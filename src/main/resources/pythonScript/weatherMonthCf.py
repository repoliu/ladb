# -*- coding: utf-8 -*-

import time  # 引入time模块
from numpy import *
import sys
import corrcoef as crf
import tempRain
from util.readProperties import *
import psycopg2
from util.log import *
reload(sys)
sys.setdefaultencoding('utf-8')

def producePoint(num):
    array=[]
    for x in xrange(num):
        array.insert(x,"F"+str(x*6)+"0")
    return  array

#用于计算舒适度
def computeCosiness(wp,temperature,humidity):
    if (wp==None):
        wp=0
    if (temperature==None):
        temperature=0
    if (humidity==None):
        humidity=0
    ws = sumWsByWp(wp)
    cosi=(int)((1.818*temperature+ 18.18)*(0.88+0.002*humidity/100)+(temperature- 32)/(45 -temperature)-3.2*ws+ 18.2)
    return  cosi
def sumWsByWp(wp):
    result = 0
    if (wp==0):
        result = 0.1
    elif(wp==1):
        result = 0.9
    elif wp==2:
        result = 2.45
    elif wp==3:
        result = 4.4
    elif wp==4:
        result = 6.7
    elif wp==5:
        result = 9.35
    elif wp==6:
        result = 12.3
    elif wp==7:
        result = 15.5
    elif wp==8:
        result = 18.95
    elif wp==9:
        result = 22.6
    elif wp==10:
        result = 26.45
    elif wp==11:
        result = 30.55
    elif wp==12:
        result = 34.8
    elif wp==13:
        result = 39.2
    elif wp==14:
        result = 43.8
    return  result



if __name__ == '__main__':
    # area="乐山"
    # mdate="2018"
    area=sys.argv[1].decode('GBK').encode('utf-8') #地区值，传入中文，程序会去dbarea表对应查找序号
    mdate=sys.argv[2]#传入年
    dict=readDb1Properties()
    currentSchema=dict['currentSchema']
    conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
    cur = conn.cursor()
    conn.set_session(autocommit=True)
    try:
        #删除每天的相关系数
        sqlDayDelete="select "+currentSchema+".p_delete('"+currentSchema+"','relation_support','" \
                     "relation_type in(''降雨量'',''温度'',''人体舒适度'',''湿度'') and " \
                     "areaname=''"+area+"'' and LENGTH(mdate)=8 and " \
                      "SUBSTRING(mdate,0,5)="+mdate+"')"
        #删除每月的相关系数
        sqlMonthDelete="select "+currentSchema+".p_delete('"+currentSchema+"','relation_support','areaname=''"+area+"'' and relation_type " \
                        "in(''降雨量'',''温度'',''人体舒适度'',''湿度'',''最低温度'',''最大降雨量'') and LENGTH(mdate)=6 and " \
                       "SUBSTRING(mdate,0,5)="+mdate+"')"
        cur.execute(sqlDayDelete)
        cur.execute(sqlMonthDelete)
                #===================负荷值筛选==============================
        #======查出的负荷数据
        sql1=" select distinct area,mdate,"
        for point in producePoint(25):
            sql1+=point+","
        sql1=sql1[:-1]+" "
        sql1+="from "+currentSchema+".load_hisdata where area=(select area from "+currentSchema+".dbarea where dname='"+area+"') " \
              "and SUBSTRING(mdate,0,5) ='"+mdate+"' order by mdate"
        start = time.time()
        dict={} #产生温度
        cur.execute(sql1)
        loadData=cur.fetchall()



        #========查询表中一天中存在多少个小时
        #autovalue中应记录每天24小时的时间点，实际出现大于或小于24小时的情况，
        # 此sql用于排除重复情况，值查询小于24小时的计数
        sql2="select dates,count(*) from (select distinct to_char(time,'yyyymmdd') dates," \
             "to_char(time,'hh24') from "+currentSchema+".autovalue " \
             "where to_char(time,'yyyy')='"+mdate+"' and stcd = " \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
              "order by dates ) a group by dates order by dates"
        cur.execute(sql2)
        hourCountData=cur.fetchall()
        dayDict={}
        #形成每天对应多少个小时的数据字典
        for data in hourCountData:
            dayDict[data[0]]=data[1]

        #========查询表中一月中存在多少个天
        sql3="select to_char(to_date(dates,'yyyymmdd'),'yyyymm') date2,count(to_char(to_date(dates,'yyyymmdd'),'yyyymm')) " \
             "from (select distinct to_char(time,'yyyymmdd') dates from "+currentSchema+".autovalue " \
             "where to_char(time,'yyyy') ='"+mdate+"' and stcd = " \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) order by dates ) a " \
             "group by date2 order by date2"
        cur.execute(sql3)

        monthDayLength=cur.fetchall()
        list_2d = [[0 for col in xrange(7)] for row in xrange(len(monthDayLength))]
        for index in xrange(len(monthDayLength)):
            list_2d[index]=[0 for col in xrange(monthDayLength[index][1])]

        #=====查询一天中存在的小时数的具体数值
        sql4="select distinct to_char(time,'yyyymmdd'),to_char(time,'hh24') from "+currentSchema+".autovalue " \
             "where to_char(time,'yyyy') ='"+mdate+"'  and " \
            "stcd = (select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
            "order by to_char(time,'yyyymmdd'),to_char(time,'hh24') "
        cur.execute(sql4)
        hourDetailDate=cur.fetchall()
        hourData=[0 for col in xrange(len(hourDetailDate))]
        #将查到的一年的每天小时数遍历到一个列表中
        for i in xrange(len(hourDetailDate)):
            hourData[i]=int(hourDetailDate[i][1])

        hourCount=0
        dayHourDict={}#每天在数据库存在的具体时间
        #按照每天的的形式输出每天存在的小时数，此处应该被遍历的日期
        for data in hourCountData:
            if dayDict.has_key(data[0]):
                count=dayDict[data[0]]
                dayHourDict[data[0]]=hourData[hourCount:hourCount+count]
                hourCount+=count
        #查询表中所查询年中存在的月
        sql5="select distinct to_char(time,'yyyymm') as month from "+currentSchema+".autovalue " \
             "where to_char(time,'yyyy') ='"+mdate+"'  and stcd = " \
            "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) order by month"
        cur.execute(sql5)
        monthList=cur.fetchall()
        monthDict={}
        monthCount=0

        for data in monthList:
            monthDict[data[0]]=data #初始化月字典的数据
            daycount=0
            dictTemp={}
            for  data2 in dayHourDict:
                if data[0]==data2[0:6]:
                    dictTemp[data2]=dayHourDict[data2]
                    list_2d[monthCount][daycount]=dictTemp
                    daycount+=1
            monthCount+=1
            #print daycount
        yearDict={}
        #======将各个月字典数据放进年字典
        for index in  xrange(len(monthList)):
            yearDict[monthList[index][0]]=list_2d[index][0] #添加坐标0是因为list_2d内的字典在列表上
        #用于查询一年表中有多少天的数据
        sql6="select count(*) from (select distinct to_char(time,'yyyymmdd') from "+currentSchema+".autovalue " \
             "where to_char(time,'yyyy') ='"+mdate+"' and stcd = " \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
             "order by to_char(time,'yyyymmdd')) a"
        cur.execute(sql6)

        yearDayCount=cur.fetchall()#取到的是列表中的元组数据，所以要用yearDayCount[0][0]取出唯一的一个数
        loadCompareData=[]
        for  data in loadData:
            if yearDict.get(data[1][0:6])!=None and yearDict.get(data[1][0:6]).get(data[1])!=None:
                count=2
                loadTempData=[0 for col in xrange(count+len(yearDict.get(data[1][0:6]).get(data[1])))]
                loadTempData[0:count]=data[0:count]
                for  index in yearDict[data[1][0:6]][data[1]]:
                    loadTempData[count]=data[index+2]
                    count+=1
                loadCompareData.append(loadTempData)
        # ！！！！loadCompareData为待计算的负荷数据
        #========================================================================

        sql7="select distinct to_char(time,'yyyy-mm-dd hh24:mi:ss'),wp,temperature,humidity,rain from "+currentSchema+".autovalue " \
             "where to_char(time,'yyyy') ='"+mdate+"' and stcd = " \
             "(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) order by to_char(time,'yyyy-mm-dd hh24:mi:ss') "
        cur.execute(sql7)

        weatherInfo=cur.fetchall()
        weatherCompareInfo=[0 for col in xrange(len(weatherInfo))]
        #将计算后的舒适度插入到列表中
        for index in xrange(len(weatherInfo)):
            weatherCompareInfo[index]=list(weatherInfo[index])
            weatherCompareInfo[index].append(computeCosiness(weatherInfo[index][1],weatherInfo[index][2],weatherInfo[index][3]))
            #weatherCompareInfo中的数据包括time,wp风速,temperature温度,humidity湿度,rain降雨量,最后添加了舒适度
        #每天在数据库存在的具体时间
        weatherTempInfo=map(list,zip(*weatherCompareInfo))#将列转行后的数据复制给此变量
        weatherCompareInfo2 = [[0 for col in xrange(len(weatherTempInfo)-1)] for row in xrange(len(hourCountData))]
        sql8="select area,dname from "+currentSchema+".dbarea where dname='"+area+"'"
        cur.execute(sql8)
        areainfo=cur.fetchall()
        dbarea=areainfo[0][0] #当地区为单个时查出存在的对应的日期编码
        dname=areainfo[0][1]
        #按照每天的的形式输出每天存在的小时数，此处应该被遍历的日期

        hourCount=0
        insertNum=3
        #weatherCompareInfo2存储的数据0 风速,1 温度,2 湿度,3降雨,4 舒适度
        for index in xrange(len(hourCountData)):
            if dayDict.has_key(hourCountData[index][0]):
                count=dayDict[hourCountData[index][0]]
                for index2 in xrange(len(weatherTempInfo)-1):
                    weatherCompareInfo2[index][index2]=weatherTempInfo[index2+1][hourCount:hourCount+count] #将自带的时间信息筛去
                    weatherCompareInfo2[index][index2].insert(0,hourCountData[index][0])
                    weatherCompareInfo2[index][index2].insert(1,dbarea)
                    weatherCompareInfo2[index][index2].insert(2,dname)
                hourCount+=count
        #！！！！！！！！！weatherCompareInfo2为待计算的天气数据，是按照月结构区分的
        #print weatherCompareInfo2

	    insertSql="insert into "+currentSchema+".relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values "
        nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
        for index in xrange(len(loadCompareData)):
            # insertSql+="('"+str(weatherCompareInfo2[index][3][1])+"','"+str(weatherCompareInfo2[index][3][1])+"','" \
            #            +str(weatherCompareInfo2[index][3][2])+"','"+str(weatherCompareInfo2[index][3][0])+"','"+ \
            #            "降雨量"+"','"+str(crf.corrcoef(loadCompareData[index][2:],weatherCompareInfo2[index][3][insertNum:]))+"','"+ \
            #            str(nowtime)+"'),"
            insertSql+="('"+str(weatherCompareInfo2[index][3][1])+"','"+str(weatherCompareInfo2[index][3][1])+"','" \
                       +str(weatherCompareInfo2[index][3][2])+"','"+str(weatherCompareInfo2[index][3][0])+"','"+ \
                       "温度"+"','"+str(crf.corrcoef(loadCompareData[index][2:],weatherCompareInfo2[index][1][insertNum:]))+"','"+ \
                       str(nowtime)+"'),"
            insertSql+="('"+str(weatherCompareInfo2[index][3][1])+"','"+str(weatherCompareInfo2[index][3][1])+"','" \
                       +str(weatherCompareInfo2[index][3][2])+"','"+str(weatherCompareInfo2[index][3][0])+"','"+ \
                       "人体舒适度"+"','"+str(crf.corrcoef(loadCompareData[index][2:],weatherCompareInfo2[index][4][insertNum:]))+"','"+ \
                       str(nowtime)+"'),"
            insertSql+="('"+str(weatherCompareInfo2[index][3][1])+"','"+str(weatherCompareInfo2[index][3][1])+"','" \
                       +str(weatherCompareInfo2[index][3][2])+"','"+str(weatherCompareInfo2[index][3][0])+"','"+ \
                       "湿度"+"','"+str(crf.corrcoef(loadCompareData[index][2:],weatherCompareInfo2[index][2][insertNum:]))+"','"+ \
                       str(nowtime)+"'),"
        if(len(loadCompareData)!=0):
            cur.execute(insertSql[:-1])

        #调用tempRain模块，插入降雨量和温度与负荷相关性系数

        tempRain.Covariance(area,mdate)

        sql9="select substring(mdate,0,7) mdate,AREA,ID,AREANAME,relation_type,avg(RELATION_DEGREE) " \
             "from "+currentSchema+".relation_support where relation_type in('人体舒适度','温度','湿度') " \
             "and areaname='"+area+"' and substring(mdate,0,5)='"+mdate+"' group by substring(mdate,0,7)," \
             "relation_type,AREA,ID,AREANAME order by substring(mdate,0,7)"
        cur.execute(sql9)
        monthAvg=cur.fetchall()
        insertSql2="insert into "+currentSchema+".relation_support(mdate,area,id,areaname,relation_type,relation_degree,ver_time) values"

        for data in  monthAvg:
                insertSql2+="('"+str(data[0])+"','"+str(data[1])+"','"+str(data[2])+"','"+str(data[3])+"','" \
                            +str(data[4])+"','"+str(data[5])+"','"+str(nowtime)+"'),"
        if(len(monthAvg)!=0):
            cur.execute(insertSql2[:-1])
            print True
        else:
            print False

    except BaseException as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    finally:
        conn.close()
