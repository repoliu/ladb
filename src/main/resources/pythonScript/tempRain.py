# -*- coding: utf-8 -*-

import time  # 引入time模块
import sys
from numpy import *
import corrcoef as crf
from util.readProperties import *
import psycopg2
import copy
from util.log import *
reload(sys)
sys.setdefaultencoding('utf-8')

# for i in range(1, len(sys.argv)):
#          province=sys.argv[1]
#          print (province)
def producePoint(num):
    array=[]
    for x in xrange(num):
        array.insert(x,"F"+str(x*6)+"0")
    return  array



#if __name__ == '__main__':
def Covariance(area,mdate): #参数1 地点 参数2 年份
    dict=readDb1Properties()
    currentSchema=dict['currentSchema']
    conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
    cur = conn.cursor()
    conn.set_session(autocommit=True)
    try:

        area=str(area)
        mdate=str(mdate)
        #查询某年有哪些月份，封装为[月]，等待遍历使用
        sql0="select distinct to_char(time, 'yyyymm') from "+currentSchema+".autovalue where " \
             "to_char(time, 'yyyy') ='"+mdate+"' and stcd =" \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
             "order by  to_char(time, 'yyyymm') "
        cur.execute(sql0)
        exitMonth=cur.fetchall()

        #==============降雨、负荷相关性每月的天值相加求平均值，转为字典储值对比值
        #降雨的精确到每小时值大于0的sql，存储为{日期点：[值]}
        sql1="select distinct to_char(time, 'yyyymmddhh24'),rain from "+currentSchema+".autovalue " \
             "where to_char(time, 'yyyy') ='"+mdate+"' and stcd =" \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) and rain>0 " \
             "order by to_char(time, 'yyyymmddhh24')"
        cur.execute(sql1)
        hourRain=cur.fetchall()
        hourRainDict={}
        for data in hourRain:
            hourRainDict[data[0]]=data[1]


        #降雨值大于0时的每天时间点，转为{日期：[时间点]}字典存储
        sql2="select distinct to_char(time, 'yyyymmdd') as  day,to_char(time, 'hh24') " \
             "from "+currentSchema+".autovalue where to_char(time, 'yyyy') ='"+mdate+"' and stcd =" \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) and rain>0 " \
             "order by day"
        cur.execute(sql2)
        dateHour=cur.fetchall()
        dateHourDict={}
        for data in  dateHour:
            dateHourDict[data[0]]=[] #初始化
        for data in dateHour:
            dateHourDict[data[0]].append(data[1])

        #降雨值>0时每月有哪些天，转为{月：[日期]}字典存储
        sql3="select distinct to_char(time, 'yyyymm'),to_char(time, 'yyyymmdd') " \
             "from "+currentSchema+".autovalue where to_char(time, 'yyyy') ='"+mdate+"' and stcd =" \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) and rain>0 " \
             "order by to_char(time, 'yyyymmdd')"
        cur.execute(sql3)
        monthData=cur.fetchall()
        monthDataDict={}

        for data in exitMonth:
            monthDataDict[data[0]]={}
        for data in monthData:
                monthDataDict[data[0]][data[1]]=[]
        #{月：[日期]} {日期：[时间点]}核对为字典形式为{月：{日期：[时间点]}}
        monthDataHourDict={}

        for data in dateHourDict.keys():#遍历出每一个日期
            if monthDataDict.has_key(data[0:6]):#取每一个日期的月份
                monthDataDict[data[0:6]][data]=dateHourDict[data] #定位到字典的日期并赋值
        #{月：{日期：[时间点]}}将sql1的结果核对为{月：{日期：[值]}}，降雨值封装完毕
        rainDict=copy.deepcopy(monthDataDict)#{月：{日期：[值]}}初始化,深拷贝，变成两个独立对象

        for data in monthDataDict.keys():
            for data2 in monthDataDict[data].keys():
                for index in xrange(len(monthDataDict[data][data2])):
                    rainDict[data][data2][index]=hourRainDict[str(data2)+str(monthDataDict[data][data2][index])]


        #查询某一年全部的负荷数据，转为{日期[值]}
        sql4=" select distinct area,mdate,"
        for point in producePoint(25):
            sql4+=point+","
        sql4=sql4[:-1]+" "
        sql4+="from "+currentSchema+".load_hisdata where area=(select area from "+currentSchema+".dbarea where dname='"+area+"') " \
              "and substring(mdate,0,5) ='"+mdate+"' order by mdate"
        cur.execute(sql4)
        loadData=cur.fetchall()
        startIndex=2#初始取值下标
        #{月：{日期：[时间点]}}将sql4转化为{月：{日期：[值]}}，负荷值封装完毕
        loadDict=copy.deepcopy(monthDataDict)#要对比的日期数据结构需要相同

        for data in loadData:#遍历出负荷每条信息
            if  monthDataDict.has_key(data[1][0:6]) and monthDataDict[data[1][0:6]].has_key(data[1]):
                for data2 in  monthDataDict[data[1][0:6]][str(data[1])]:#遍历出待取的时间点集合，取值时应加2
                        index=monthDataDict[data[1][0:6]][str(data[1])].index(data2)
                        loadDict[data[1][0:6]][data[1]][index]=float(data[int(data2)+startIndex])


        #拼接插入的sql时计算值
        insertSql1="insert into "+currentSchema+".relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values "
        nowtime=time.strftime('%Y-%m-%d',time.localtime(time.time()))
        for data in loadDict.keys():
            for data2 in loadDict[data].keys():
                insertSql1+="('"+str(loadData[0][0])+"','"+str(loadData[0][0])+"','"+area+"','"\
                            +str(data2)+"','"+"降雨量"+"','"+str(crf.corrcoef(loadDict[data][data2],rainDict[data][data2]))+"','"+ \
                        str(nowtime)+"'),"

        if(len(loadDict)>0):
            cur.execute(insertSql1[:-1])
        #插入数据库


        #============最大负荷、最低温度相关性每月有天数个点，字典储值
        #查询最低温度sql，封装为{日期：[值]}
        sql5="select distinct to_char(time, 'yyyymmdd') as day,min(temperature) from "+currentSchema+".autovalue " \
             "where to_char(time, 'yyyy') ='"+mdate+"' and stcd=" \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
             "group by to_char(time, 'yyyymmdd') order by day "
        cur.execute(sql5)
        minTemperatureList=cur.fetchall()
        TemperatureDateDict={}
        for data in minTemperatureList:
            TemperatureDateDict[data[0]]=data[1]

        #查询最大负荷，封装为{日期：[值]}  列表筛选sql4结果最大值
        loadDateDict={}
        for data in loadData:
            if  TemperatureDateDict.has_key(data[1]):
                loadDateDict[data[1]]=float(max(list(data[2:]))) #负荷的结果数量要被
        #sql5结果与sql0对比，封装为{月：[值]}-A
        minTemperatureDict={}
        for data in exitMonth:
            minTemperatureDict[data[0]]=[]
        for data in TemperatureDateDict.keys():#遍历出每一个日期
                minTemperatureDict[data[0:6]].append(TemperatureDateDict[data]) #定位到字典的日期并赋值

        #sql4结果最大值结果与sql0对比，封装为{月：[值]}-B
        maxLoadDict={}
        for data in exitMonth:
            maxLoadDict[data[0]]=[]
        for data in loadDateDict.keys():#遍历出每一个日期
            maxLoadDict[data[0:6]].append(loadDateDict[data]) #定位到字典的日期并赋值

        #{月：[值]}-A 、{月：[值]}-B计算值，拼接insert的sql
        insertSql2="insert into "+currentSchema+".relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values "
        nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
        for data in exitMonth:
            insertSql2+="('"+str(loadData[0][0])+"','"+str(loadData[0][0])+"','"+area+"','" \
                        +str(data[0])+"','"+"最低温度"+"','"+str(crf.corrcoef(maxLoadDict[data[0]],minTemperatureDict[data[0]]))+"','"+ \
                        str(nowtime)+"'),"
        #插入数据库
        if(len(exitMonth)>0):
            cur.execute(insertSql2[:-1])#温度月系数插入

        sql9="select substring(mdate,0,7) as mdate,AREA,ID,AREANAME,relation_type,avg(RELATION_DEGREE) " \
             "from "+currentSchema+".relation_support where relation_type in('降雨量') " \
             "and areaname='"+area+"' and substring(mdate,0,5)='"+mdate+"' group by substring(mdate,0,7)," \
             "relation_type,AREA,ID,AREANAME order by mdate"
        cur.execute(sql9)
        monthAvg=cur.fetchall()
        insertSql3="insert into "+currentSchema+".relation_support(mdate,area,id,areaname,relation_type,relation_degree,ver_time) values"
        for data in  monthAvg:
            insertSql3+="('"+str(data[0])+"','"+str(data[1])+"','"+str(data[2])+"','"+str(data[3])+"','" \
                        +str(data[4])+"','"+str(data[5])+"','"+str(nowtime)+"'),"
        if(len(monthAvg)>0):
            cur.execute(insertSql3[:-1]) #降雨量温度系数插入

        # ============最大负荷、最大降雨量相关性每月有天数个点，字典储值
        # 查询最大降雨量sql，封装为{日期：[值]}
        sql_1="select distinct to_char(time,'yyyymmdd') as time,max(rain) from "+currentSchema+".autovalue " \
              "where to_char(time,'yyyy')='"+mdate+"'and stcd=" \
              "(select stcd from "+currentSchema+".qstation where stnm='"+area+"') " \
              "and rain > 0 group by to_char(time,'yyyymmdd') order by time"
        cur.execute(sql_1)
        maxRainList = cur.fetchall()
        RainDataDict = {}
        for data in maxRainList:
            RainDataDict[data[0]] = data[1]

        # 查询最大负荷，封装为{日期：[值]}  列表筛选sql4结果最大值
        loadDateDict.clear()
        for data in loadData:
            if RainDataDict.has_key(data[1]):
                loadDateDict[data[1]] = float(max(list(data[2:])))  # 负荷的结果数量要被

        # sql5结果与sql0对比，封装为{月：[值]}-A
        maxRainDict = {}
        for data in exitMonth:
            maxRainDict[data[0]] = []
        for data in RainDataDict.keys():  # 遍历出每一个日期
            maxRainDict[data[0:6]].append(RainDataDict[data])  # 定位到字典的日期并赋值

        # sql4结果最大值结果与sql0对比，封装为{月：[值]}-B
        maxLoadDict.clear()
        for data in exitMonth:
            maxLoadDict[data[0]] = []
        for data in loadDateDict.keys():  # 遍历出每一个日期
            maxLoadDict[data[0:6]].append(loadDateDict[data])  # 定位到字典的日期并赋值

        # {月：[值]}-A 、{月：[值]}-B计算值，拼接insert的sql
        insertSql_1 = "insert into "+currentSchema+".relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values"
        nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
        for data in exitMonth:
            insertSql_1 += "('" + str(loadData[0][0]) + "','" + str(loadData[0][0]) + "','" + area + "','" \
                           + str(data[0]) + "','" + "最大降雨量" + "','" + str(
                crf.corrcoef(maxLoadDict[data[0]], maxRainDict[data[0]])) + "','" + \
                           str(nowtime) + "'),"
        if(len(exitMonth)>0):
        #插入数据库
            cur.execute(insertSql_1[:-1])  # 最大降雨量系数插入

    except BaseException as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    finally:
        
        conn.close()
