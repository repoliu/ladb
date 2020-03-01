# -*- coding: utf-8 -*-
#基于大数据的负荷分析程序员手册3.5.1
#依赖情况：因为需要相关系数，即需要先产生相关系数再进行值的计算
import sys

import time

from dataPackage.dataPack import transBracket
from util.readProperties import *
from util.log import *
import psycopg2


#.若相关系数最大的有多个，取温度最大的；
# 若温度最大相同，取最近温度最大的。
# 若温度最大相同，则取此几天中最低温度最大的几天
#此处写逻辑处理函数
def producePoint(num):
    array=[]
    for x in xrange(num):
        if(x<1):
            array.insert(x,"avg(F00)")
        else:
            array.insert(x,"avg(F"+str(x*15)+")")
    return  array

#处理年数据
def airConditionYear(area):
    try:
        stcdSql = "select stcd from " + currentSchema + ".qstation where stnm='" + area + "' "
        cur.execute(stcdSql)
        stcd = str(cur.fetchall()[0][0])
        areaSql="select area from  "+ currentSchema + ".dbarea where dname='"+area+"'"
        cur.execute(areaSql)
        areaNumber = str(cur.fetchall()[0][0])
        #每次计算前删除一次指定地区时间数据
        sqlDayDelete="select "+currentSchema+".p_delete('"+currentSchema+"','load_airconditioner_analysis','" \
                      "area=''"+areaNumber+"'' and mdate="+mdate+"')"
        cur.execute(sqlDayDelete)
        #RDB中用于计算日期是周几的 函数 select mod((date('2018-09-25')-date('1920-01-01'))-3,7)
        sql0="select mdate,mod((date(mdate)-date('1920-01-01'))-3,7) as weekday from "+ currentSchema + ".relation_support " \
              "where areaname='"+area+"' and  substring(mdate,0,5)='"+mdate+"' and length(mdate)=8 and relation_type='温度'  and " \
               "relation_degree=(select max(relation_degree) from "+currentSchema+ ".relation_support where " \
              "areaname='"+area+"' and  substring(mdate,0,5)='"+mdate+"' and length(mdate)=8 and relation_type='温度') order by mdate asc"
        #此处是出于工作日非工作日的情况不需要考虑
        cur.execute(sql0)
        print sql0
        bdayinfo=cur.fetchall()
        bday=""#bday初始化
        weekDay=""
        #=====相关系数相同时所需参数
        maxTemperature=0
        countIndex=0

        #若bday查无数据则退出计算
        if (len(bdayinfo)==0):
            print "bday查无数据"
            sys.exit()
        elif(len(bdayinfo)==1):
            bday=bdayinfo[0][0]
            weekDay=str(bdayinfo[0][1])
        elif(len(bdayinfo)>1): #温度与负荷相关系数最大的日期可能有多个
            dayList=[] #为温度最大相同可能有多天情况准备
            for data in bdayinfo:
                dayList.append(data[0])
            sql2="select DISTINCT to_char(time,'yyyyMMdd'),mod((date(time)-date('1920-01-01'))-3,7) from "+currentSchema+ ".autovalue where temperature=( " \
                  "select max(temperature) from "+currentSchema+ ".autovalue where stcd="+stcd+" and to_char(time,'yyyyMMdd') in "+transBracket(dayList)+")  " \
                  "and stcd="+stcd+" and to_char(time,'yyyyMMdd') in "+transBracket(dayList)+""
            cur.execute(sql2)
            bdayinfo=cur.fetchall()  #第二次筛选后的bday

            if(len(bdayinfo)==1):
                bday=bdayinfo[0][0]
                weekDay=str(bdayinfo[0][1])
            elif(len(bdayinfo)>1):#温度最大相同可能有多天
                dayList=[] #为温度最大相同可能有多天情况准备
                for data in bdayinfo:
                    dayList.append(data[0])
                    #此处依旧可能出错，最低温度最大的一天也可能有多天

                sql3="select distinct min(temperature) minTemp,to_char(time,'yyyyMMdd'),mod((date(time)-date('1920-01-01'))-3,7) " \
                     "from "+currentSchema+ ".autovalue where to_char(time,'yyyyMMdd')  in "+transBracket(dayList)+" and stcd="+stcd+" " \
                    "group by to_char(time,'yyyyMMdd'),mod((date(time)-date('1920-01-01'))-3,7) order  by minTemp desc"
                cur.execute(sql3)
                bdayinfo=cur.fetchall()#第三次筛选后的bday
                if(len(bdayinfo)>1):
                    bday=bdayinfo[0][1]
                    weekDay=str(bdayinfo[0][2])
                    print "最低温度最大的的情况也可能有多天"
        #===============开始查询aday、cday=======
        aDaySql="select mdate from "+currentSchema+ ".relation_support where areaname='"+area+"' and  substring(mdate,0,5)="+mdate+"  " \
                "and length(mdate)=8 and relation_degree<50  and  relation_type='温度' and " \
                "mod((date(mdate)-date('1920-01-01'))-3,7)="+weekDay+" and mdate<'"+bday+"'   order by mdate desc limit 1"
        cur.execute(aDaySql)
        adayinfo=cur.fetchall()
        cDaySql="select mdate from "+currentSchema+ ".relation_support where areaname='"+area+"' and  substring(mdate,0,5)="+mdate+"  " \
                "and length(mdate)=8 and relation_degree<50  and  relation_type='温度' and " \
                "mod((date(mdate)-date('1920-01-01'))-3,7)="+weekDay+" and mdate>'"+bday+"'   order by mdate asc limit 1"
        cur.execute(cDaySql)
        cdayinfo=cur.fetchall()
        if len(adayinfo)>0 and len(cdayinfo)>0:
            sql4="select "
            for data in producePoint(96):
                sql4+=data+","
            dsql=sql4[:-1]+" from "+currentSchema+ ".load_hisdata where area="+areaNumber+" and mdate in('"+adayinfo[0][0]+"','"+cdayinfo[0][0]+"')"
            cur.execute(dsql)
            dgraphinfo=cur.fetchall()


            sql5="select "
            for data in producePoint(96):
                sql5+=data+","
            bsql=sql5[:-1]+" from "+currentSchema+ ".load_hisdata where area="+areaNumber+" and mdate ="+bday+""
            cur.execute(bsql)
            bgraphinfo=cur.fetchall()
            egraphList=[]
            for index in xrange(len(bgraphinfo[0][0:])):
                egraphList.append(bgraphinfo[0][index] -dgraphinfo[0][index])
            eElectric=0 #空调负荷最大电量
            if len(egraphList)>0:
                eElectric=sum(egraphList)/4
            bElectric=0 #空调负荷占比最大日电量
            maxBgraph=0 #空调负荷占比最大日最大负荷
            if len(bgraphinfo)>0:
                bElectric=sum(bgraphinfo[0][0:])/4
                maxBgraph='%.2f' %  max(bgraphinfo[0][0:])
            airMaxPercent=0 #空调负荷最大占比变量
            if bElectric!=0:
                airMaxPercent='%.2f' % ((eElectric/bElectric)*100)
            nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
            insertSql="insert into "+currentSchema+ ".load_airconditioner_analysis (area,mdate,aday,bday,cday,result_type,result,ver_time) values "
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷最大占比','"+str(airMaxPercent)+"','"+str(nowtime)+"'),"
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷最大电量','"+'%.2f' % (eElectric)+"','"+str(nowtime)+"'),"
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷占比最大日电量','"+'%.2f' % (bElectric)+"','"+str(nowtime)+"'),"
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷占比最大日最大负荷','"+maxBgraph +"','"+str(nowtime)+"')"
            cur.execute(insertSql)
            print True
        else:
            print  "aday或cday查无数据"

    except BaseException as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))


#处理月数据
def airConditionMonth(area):
    try:
        stcdSql = "select stcd from " + currentSchema + ".qstation where stnm='" + area + "' "
        cur.execute(stcdSql)
        stcd = str(cur.fetchall()[0][0])
        areaSql="select area from  "+ currentSchema + ".dbarea where dname='"+area+"'"
        cur.execute(areaSql)
        areaNumber = str(cur.fetchall()[0][0])
        #每次计算前删除一次指定地区时间数据
        sqlDayDelete="select "+currentSchema+".p_delete('"+currentSchema+"','load_airconditioner_analysis','" \
                             "area=''"+areaNumber+"'' and mdate="+mdate+"')"
        cur.execute(sqlDayDelete)
        #RDB中用于计算日期是周几的 函数 select mod((date('2018-09-25')-date('1920-01-01'))-3,7)
        sql0="select mdate,mod((date(mdate)-date('1920-01-01'))-3,7) as weekday from "+ currentSchema + ".relation_support " \
              "where areaname='"+area+"' and  substring(mdate,0,7)='"+mdate+"' and length(mdate)=8  and relation_type='温度' and " \
              "relation_degree=(select max(relation_degree) from "+currentSchema+ ".relation_support where " \
              "areaname='"+area+"' and  substring(mdate,0,7)='"+mdate+"' and length(mdate)=8 and relation_type='温度') order by mdate asc"
        #此处是出于工作日非工作日的情况不需要考虑
        cur.execute(sql0)
        bdayinfo=cur.fetchall()
        bday=""#bday初始化
        weekDay=""

        #若bday查无数据则退出计算
        if (len(bdayinfo)==0):
            print "bday查无数据"
            sys.exit()
        elif(len(bdayinfo)==1):
            bday=bdayinfo[0][0]
            weekDay=str(bdayinfo[0][1])
        elif(len(bdayinfo)>1): #温度与负荷相关系数最大的日期可能有多个
            dayList=[] #为温度最大相同可能有多天情况准备
            for data in bdayinfo:
                dayList.append(data[0])
            sql2="select DISTINCT to_char(time,'yyyyMMdd'),mod((date(time)-date('1920-01-01'))-3,7) from "+currentSchema+ ".autovalue where temperature=( " \
                 "select max(temperature) from "+currentSchema+ ".autovalue where stcd="+stcd+" and to_char(time,'yyyyMMdd') in "+transBracket(dayList)+" )  " \
                 "and stcd="+stcd+" and to_char(time,'yyyyMMdd') in "+transBracket(dayList)+""
            cur.execute(sql2)
            bdayinfo=cur.fetchall()
            dayList=[] #为温度最大相同可能有多天情况准备
            if(len(bdayinfo)==1):
                bday=bdayinfo[0][0]
                weekDay=str(bdayinfo[0][1])
            elif(len(bdayinfo)>1):#温度最大相同可能有多天,
                for data in bdayinfo:
                    dayList.append(data[0])
                    #此处依旧可能出错，最低温度最大的一天也可能有多天
                sql3="select distinct min(temperature) minTemp,to_char(time,'yyyyMMdd'),mod((date(time)-date('1920-01-01'))-3,7) " \
                     "from "+currentSchema+ ".autovalue where to_char(time,'yyyyMMdd')  in "+transBracket(dayList)+" and stcd="+stcd+" " \
                     "group by to_char(time,'yyyyMMdd'),mod((date(time)-date('1920-01-01'))-3,7) order  by minTemp desc"
                cur.execute(sql3)
                bdayinfo=cur.fetchall()
                if(len(bdayinfo)>1):
                    bday=bdayinfo[0][1]
                    weekDay=str(bdayinfo[0][2])
                    print "最低温度最大的的情况也有多天"
        #===============开始查询aday、cday=======
        aDaySql="select mdate from "+currentSchema+ ".relation_support where areaname='"+area+"' and  substring(mdate,0,7)="+mdate+"  " \
                "and length(mdate)=8 and relation_degree<50  and  relation_type='温度' and " \
                "mod((date(mdate)-date('1920-01-01'))-3,7)="+weekDay+" and mdate<'"+bday+"'   order by mdate desc limit 1"
        cur.execute(aDaySql)
        adayinfo=cur.fetchall()
        cDaySql="select mdate from "+currentSchema+ ".relation_support where areaname='"+area+"' and  substring(mdate,0,7)="+mdate+"  " \
                "and length(mdate)=8 and relation_degree<50  and  relation_type='温度' and " \
                "mod((date(mdate)-date('1920-01-01'))-3,7)="+weekDay+" and mdate>'"+bday+"'   order by mdate asc limit 1"
        cur.execute(cDaySql)
        cdayinfo=cur.fetchall()
        if len(adayinfo)>0 and len(cdayinfo)>0:
            sql4="select "
            for data in producePoint(96):
                sql4+=data+","
            dsql=sql4[:-1]+" from "+currentSchema+ ".load_hisdata where area="+areaNumber+" and mdate in('"+adayinfo[0][0]+"','"+cdayinfo[0][0]+"')"
            cur.execute(dsql)
            dgraphinfo=cur.fetchall()


            sql5="select "
            for data in producePoint(96):
                sql5+=data+","
            bsql=sql5[:-1]+" from "+currentSchema+ ".load_hisdata where area="+areaNumber+" and mdate ="+bday+""
            cur.execute(bsql)
            bgraphinfo=cur.fetchall()
            egraphList=[]
            for index in xrange(len(bgraphinfo[0][0:])):
                egraphList.append(bgraphinfo[0][index] -dgraphinfo[0][index])
            eElectric=0 #空调负荷最大电量
            if len(egraphList)>0:
                eElectric=sum(egraphList)/4
            bElectric=0 #空调负荷占比最大日电量
            maxBgraph=0 #空调负荷占比最大日最大负荷
            if len(bgraphinfo)>0:
                bElectric=sum(bgraphinfo[0][0:])/4
                maxBgraph='%.2f' %  max(bgraphinfo[0][0:])
            airMaxPercent=0 #空调负荷最大占比变量
            if bElectric!=0:
                airMaxPercent='%.2f' % ((eElectric/bElectric)*100)
            nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
            insertSql="insert into "+currentSchema+ ".load_airconditioner_analysis (area,mdate,aday,bday,cday,result_type,result,ver_time) values "
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷最大占比','"+str(airMaxPercent)+"','"+str(nowtime)+"'),"
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷最大电量','"+'%.2f' % (eElectric)+"','"+str(nowtime)+"'),"
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷占比最大日电量','"+'%.2f' % (bElectric)+"','"+str(nowtime)+"'),"
            insertSql+="("+areaNumber+",'"+mdate+"','"+adayinfo[0][0]+"','"+bday+"','"+cdayinfo[0][0]+"','空调负荷占比最大日最大负荷','"+maxBgraph +"','"+str(nowtime)+"')"
            cur.execute(insertSql)
            print True
        else:
            print  "aday或cday查无数据"

    except Exception as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    finally:
        conn.close()




if __name__ == '__main__':
    # area="乐山"
    mdate="2018"
    # area=sys.argv[1].decode('GBK').encode('utf-8') 此处java直接调用python脚本传过来，再进行转码
    # area=sys.argv[1].decode('GBK').encode('utf-8')#地区值，传入中文，程序会去dbarea表对应查找序号
    # mdate=sys.argv[2]#传入年
    dict=readDb1Properties()
    currentSchema=dict['currentSchema']
    conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
    cur = conn.cursor()
    conn.set_session(autocommit=True)
    cur.execute("select dname from "+currentSchema+".dbarea where parent_id=32")
    dname = cur.fetchall()
    for data in dname:
        airConditionYear(data[0])
    # if len(mdate)==4:
    #     airConditionYear(area)
    # elif len(mdate)==6:
    #     airConditionMonth(area)
    conn.close()