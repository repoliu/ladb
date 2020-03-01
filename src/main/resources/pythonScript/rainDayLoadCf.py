# -*- coding: utf-8 -*-
import sys
import time  # 引入time模块
import dataPackage.dataPack as dataPack
import  corrcoef as crf
from util.readProperties import *
from numpy import *
from util.log import *
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("..")
import psycopg2


def producePoint(num):
            array=[]

            for x in xrange(num):
                if(x*4<10):
                    array.insert(x,"F0"+str(x*4))
                else:
                    array.insert(x,"F"+str(x*4))
            return  array

#从load_hisdata中取出对应的24小时的点
def producePoint2(num):
    array=[]
    for x in xrange(num):
        if(x==0):
            array.insert(x,"F00")
        else:
            array.insert(x,"F"+str(x*60))
    return  array


dict0=readDb1Properties()
currentSchema=dict0['currentSchema']
conn = psycopg2.connect(database=dict0['database'],user=dict0['user'],password=dict0['passwd'],host=dict0['host'],port=dict0['port'])
cur = conn.cursor()
conn.set_session(autocommit=True)


        #=========大雨、暴雨、大暴雨、特大暴雨 对应日期下的的温度、降雨、风力 与负荷的相关性分析

# if __name__=='__main__':
def start(area,mdate):
        try:
            start = time.time()
            # area="长沙"
            # mdate="2018"
            # area=sys.argv[1].decode('GBK').encode('utf-8') #地区值，传入中文，程序会去dbarea表对应查找序号
            # mdate=sys.argv[2].decode('GBK').encode('utf-8')[0:4]#传入日期，截取字符串年

            sqlDelete="select "+currentSchema+".p_delete('"+currentSchema+"','relation_support','areaname=''"+area+"'' and " \
                       "substring(mdate,0,5) =''"+mdate+"'' and relation_type like ''%大雨%'' " \
                       "or relation_type like ''%暴雨%'' or relation_type like ''%大暴雨%'' or " \
                       "relation_type like ''%特大暴雨%''' )"
            cur.execute(sqlDelete)
            
            sql0="select area from "+currentSchema+".dbarea where dname='"+area+"'"
            cur.execute(sql0)
            areainfo=cur.fetchall()
            dbarea=areainfo[0][0] #当地区为单个时查出存在的对应的地区编码
            #===================负荷值筛选==============================
            #查出日期与降雨类型的字典
            rainSql="select distinct to_char(time, 'yyyymmdd'),rain_leve from "+currentSchema+".load_rain_info  " \
                     " where  to_char(time, 'yyyy') ='"+mdate+"' and rain_leve " \
                     "in('大雨','暴雨','大暴雨','特大暴雨')"
            cur.execute(rainSql)
            rainData=cur.fetchall()
            rainDict={}
            for data in rainData:
                rainDict[data[0]]=str(data[1])
            #======查出的负荷数据，loadvalue_type=1为标志差值数据，降雨、风力、温度是有综合影响的
            sql1=" select distinct area,date(mdate),ndate,"
            for point in producePoint(25):
                sql1+=point+","
            sql1=sql1[:-1]+" "
            sql1+="from "+currentSchema+".load_hisdata_96_cluster where area='"+str(dbarea)+"' and loadvalue_type=1 " \
                   "and substring(mdate,0,5) ='"+mdate+"' and " \
                   "mdate in ( select distinct to_char(time, 'yyyymmdd') from "+currentSchema+".load_rain_info  " \
                   " where  to_char(time, 'yyyy') ='"+mdate+"' and areaname='"+area+"'  and rain_leve " \
                   "in('大雨','暴雨','大暴雨','特大暴雨') ) order by  date(mdate)"
            dict={} #产生温度
            cur.execute(sql1)
            loadData=cur.fetchall()
            loadDataFloat=[[0 for col in xrange(shape(loadData)[1])] for row in xrange(shape(loadData)[0])]
            rowMapdata={}

            #降雨日对应的未将雨日字典
            rainYN={}

            for index in xrange(len(loadData)):
                for index2 in xrange(3,shape(loadData)[1]):
                    rainYN[loadData[index][0]]=loadData[index][1]
                    loadDataFloat[index][0]=loadData[index][0] #获得第一个字段数据
                    loadDataFloat[index][1]=loadData[index][1] #获得第二个字段数据
                    if(loadData[index][index2]!=None):
                        loadDataFloat[index][index2]=float(loadData[index][index2])
                    else:
                        loadDataFloat[index][index2]=float(0)

            for data in loadDataFloat:
                dataPack.packMap(rowMapdata,0,str(data[1]).split("-"),data[2:])

            sql2="select distinct to_char(time, 'yyyy-mm-dd-hh24'),wp,temperature,rain from "+currentSchema+".load_rain_info " \
                 "where to_char(time, 'yyyy') ='"+mdate+"' and areaname ='"+area+"' " \
                 "and rain_leve in('大雨','暴雨','大暴雨','特大暴雨') " \
                 "order by to_char(time, 'yyyy-mm-dd-hh24')"
            cur.execute(sql2)
            weatherInfo=cur.fetchall()
            #列出未降雨日日期
            ndate="("
            for index in xrange(len(loadData)):
                ndate+="'"+loadData[index][2]+"',"
            if len(ndate)>1:
               ndate2=ndate[:-1]+")"
            else:
                ndate2="('')"

            #通过 load_hisdata_96_cluster表获取当前地区当年降雨日未降雨日
            #拿到降雨日未降雨日到load_rain_info,autovalue两表进行查询
            #降雨等级需要在大雨及以上
            #降雨日与未将雨日的天气必须点对点对应，所以使用to_char(a.time, 'hh24')=to_char(b.time, 'hh24') 做限制
            #查出的load_rain_info,autovalue两表的数据去重，load_rain_info表天气数据减去autovalue天气数据，并求绝对值
            #得出结果集
            sql3="select distinct to_char(a.time, 'yyyy-mm-dd-hh24'),abs(a.temperature-b.temperature),abs(a.rain-b.rain),abs(a.wp-b.wp)  " \
                   "from "+currentSchema+".load_rain_info a,"+currentSchema+".autovalue b where "\
                   " b.stcd=(select stcd from "+currentSchema+".qstation where stnm='"+area+"' ) and a.areaname='"+area+"' "\
                   " and rain_leve in('大雨','暴雨','大暴雨','特大暴雨') "\
                   " and to_char(a.time, 'yyyy')='"+mdate+"' and  " \
                   "to_char(b.time, 'yyyyMMdd')=(select distinct ndate   from "+currentSchema+".load_hisdata_96_cluster where area='"+str(dbarea)+"' and mdate=to_char(a.time, 'yyyyMMdd') limit 1)  " \
                   "and to_char(a.time, 'hh24')=to_char(b.time, 'hh24') "\
                   "order by to_char(a.time, 'yyyy-mm-dd-hh24') "
            cur.execute(sql3)
            weatherCompareInfo=cur.fetchall()

            linedata={}
            linedata["风力"]={}
            linedata["温度"]={}
            linedata["降雨量"]={}




            for data in weatherCompareInfo:
                dataPack.packMap(linedata["风力"],0,str(data[0]).split("-"),data[1])
                dataPack.packMap(linedata["温度"],0,str(data[0]).split("-"),data[2])
                dataPack.packMap(linedata["降雨量"],0,str(data[0]).split("-"),data[3])



            #遍历linedata的的key并规范化输出rowMapdata和linedata[data]的数据
            insertSql="insert into "+currentSchema+".relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values "
            nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
            boolean=False
            for data in linedata.keys():
                    rowCompare=dataPack.compareMap(rowMapdata,linedata[data])
                    for key1 in rowCompare[0].keys():
                        key01Data=rowCompare[0][key1]
                        key11Data=rowCompare[1][key1]
                        for key2 in  key01Data.keys():
                            key02Data=key01Data[key2]
                            key12Data=key11Data[key2]
                            for key3 in  key02Data.keys():
                                key03Data=key02Data[key3]
                                key13Data=key12Data[key3]
                                insertSql+="('"+str(dbarea)+"','"+str(dbarea)+"','" \
                                           +area+"','"+str(key1+key2+key3)+"','"+rainDict[key1+key2+key3]+"-"+data+"','"\
                                           +str(crf.corrcoef(key03Data,key13Data))+"','"+ \
                                           str(nowtime)+"'),"
                                boolean=True
            if(boolean):
                cur.execute(insertSql[:-1])
            end = time.time()
            #print end-start

        except BaseException as e:
            s=sys.exc_info()
            logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))

        except psycopg2.DatabaseError as e:
            s=sys.exc_info()
            logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
        finally:
            
            conn.close()
