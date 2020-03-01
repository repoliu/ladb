# -*- coding: utf-8 -*-
import sys
import time  # 引入time模块
import dataPack
import  pythonScript.corrcoef as crf
from numpy import *
sys.path.append("..")
from GBaseConnector import connect, GBaseError

#用于计算舒适度
def computeCosiness(wp,temperature,humidity):
    ws = sumWsByWp(wp)
    cosi=(int)((1.818*temperature+ 18.18)*(0.88 + 0.002*humidity/100)+(temperature- 32) / (45 -temperature)- 3.2*ws+ 18.2)
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


def producePoint(num):
    array=[]
    for x in xrange(num):
        array.insert(x,"F"+str(x*6)+"0")
    return  array


config = {'host':'192.168.6.12','port':5258,'database':'loadfor',
          'user':'gbase','passwd':'gbase20110531'}
try:
    start = time.time()
    area="福州"
    mdate="2015"
    conn = connect()
    conn.connect(**config)
    cur = conn.cursor()
    sql0="select area from dbarea where dname='"+area+"'"
    cur.execute(sql0)
    areainfo=cur.fetchall()
    dbarea=areainfo[0][0] #当地区为单个时查出存在的对应的地区编码
    #===================负荷值筛选==============================
    #======查出的负荷数据
    sql1=" select area,DATE_FORMAT(mdate, '%Y-%m-%d'),"
    for point in producePoint(25):
        sql1+=point+","
    sql1=sql1[:-1]+" "
    sql1+="from load_hisdata where area='"+str(dbarea)+"' " \
    "and DATE_FORMAT(mdate, '%Y') ='"+mdate+"' order by mdate"

    dict={} #产生温度
    cur.execute(sql1)
    loadData=cur.fetchall()
    loadDataFloat=[[0 for col in xrange(shape(loadData)[1])] for row in xrange(shape(loadData)[0])]

    rowMapdata={}

    for index in xrange(len(loadData)):
        for index2 in xrange(2,shape(loadData)[1]):
            loadDataFloat[index][0]=loadData[index][0]
            loadDataFloat[index][1]=loadData[index][1]
            if(loadData[index][index2]!=None):
                loadDataFloat[index][index2]=float(loadData[index][index2])
            else:
                loadDataFloat[index][index2]=float(0)

    for data in loadDataFloat:
        dataPack.packMap(rowMapdata,0,str(data[1]).split("-"),data[2:])



    sql2="select distinct DATE_FORMAT(time, '%Y-%m-%d-%H'),wp,temperature,humidity,rain from autovalue " \
         "where DATE_FORMAT(time, '%Y') ='"+mdate+"' and stcd = " \
          "(select stcd from qstation where stnm = '"+area+"' ) order by DATE_FORMAT(time, '%Y-%m-%d-%H') "
    cur.execute(sql2)
    weatherInfo=cur.fetchall()
    weatherCompareInfo=[0 for col in xrange(len(weatherInfo))]
    #将计算后的舒适度插入到列表中
    for index in xrange(len(weatherInfo)):
        weatherCompareInfo[index]=list(weatherInfo[index])
        weatherCompareInfo[index].append(computeCosiness(weatherInfo[index][1],weatherInfo[index][2],weatherInfo[index][3]))
        #weatherCompareInfo中的数据包括time,wp风速,temperature温度,humidity湿度,rain降雨量,最后添加了舒适度

    linedata={}
    linedata["降雨量"]={}
    linedata["温度"]={}
    linedata["人体舒适度"]={}
    linedata["湿度"]={}


    for data in weatherCompareInfo:
         dataPack.packMap(linedata["降雨量"],0,str(data[0]).split("-"),data[4])
         dataPack.packMap(linedata["温度"],0,str(data[0]).split("-"),data[2])
         dataPack.packMap(linedata["人体舒适度"],0,str(data[0]).split("-"),data[5])
         dataPack.packMap(linedata["湿度"],0,str(data[0]).split("-"),data[3])
    #遍历linedata的的key并规范化输出rowMapdata和linedata[data]的数据
    insertSql="insert into relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values "
    nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
    for data in linedata.keys():
        rowCompare=dataPack.compareMap(rowMapdata,linedata[data])
        for key1 in rowCompare[0].keys():
            key01Data=dataPack.jsonToMap(rowCompare[0][key1])
            key11Data=dataPack.jsonToMap(rowCompare[1][key1])
            for key2 in  key01Data.keys():
                key02Data=dataPack.jsonToMap(key01Data[key2])
                key12Data=dataPack.jsonToMap(key11Data[key2])
                for key3 in  key02Data.keys():
                    key03Data=dataPack.jsonToMap(key02Data[key3])
                    key13Data=dataPack.jsonToMap(key12Data[key3])
                    insertSql+="('"+str(dbarea)+"','"+str(dbarea)+"','" \
                               +area+"','"+str(key1+key2+key3)+"','"+ \
                               data+"','"+str(crf.corrcoef(key03Data,key13Data))+"','"+ \
                               str(nowtime)+"'),"
    print insertSql[:-1]

    end = time.time()
    print end-start
except GBaseError.DatabaseError, err:
        print err
finally:
        conn.close()