# -*- coding: UTF-8 -*-
import sys
import os
sys.path.append(os.path.abspath(os.path.join(os.getcwd(), "../")))

import time  # 引入time模块
from numpy import *
from util.log import *
import psycopg2
from util import readProperties

reload(sys)
sys.setdefaultencoding('utf-8')



#欧式距离公式
def distEclud(vecA,vecB):
    return linalg.norm(vecA-vecB)

def randCenters(dataSet,k):
    n=shape(dataSet)[1] #shape函数是numpy.core.fromnumeric中的函数，它的功能是查看矩阵或者数组的维数。
    clustercents=mat(zeros((k,n)))
    for col in range(n):
        mincol=min(dataSet[:,col]);maxcol=max(dataSet[:,col]) #min() 方法返回给定参数的最小值，参数可以为序列。
        #max() 方法返回给定参数的最小值，参数可以为序列。
        clustercents[:,col]=mat(mincol+float(maxcol - mincol)*random.rand(k,1)) #！！！！！重点错误地区

        #random.rand(k,1)产生一个0~1之间的随机数向量：k,1表示k行1列的随机数
    return clustercents

def insertSql(arrData):
    sql2="insert into "+currentSchema+".load_weather_cluster(area,mdate,data_type,data_vlue,cluster_type,cluster_flag,ver_time) values"
    for data in arrData:
        sql2+="("+str(data[0])+",'"+str(data[1])+"','"+str(data[2])+"',"+str(data[3])+","+str(data[4])+",'"+str(data[5])+"','"+str(data[6])+"'),"
    return sql2
#===主程序
typedata=[]
#===主程序

dict0=readProperties.readDb1Properties()
currentSchema=dict0['currentSchema']
conn = psycopg2.connect(database=dict0['database'],user=dict0['user'],password=dict0['passwd'],host=dict0['host'],port=dict0['port'])
cur = conn.cursor()
conn.set_session(autocommit=True)


class cluster:


    #area=sys.argv[1].decode('GBK').encode('utf-8') #地区值，传入中文，程序会去dbarea表对应查找序号
    #mdate=sys.argv[2].decode('GBK').encode('utf-8')[0:4]#传入日期，截取字符串年

    def start(self, area, mdate):
        try:
            start=time.time()
            sqlDelete="select "+currentSchema+".p_delete('"+currentSchema+"','load_weather_cluster','substring(mdate,0,5)=''"+mdate+"'' and area=(select area from "+currentSchema+".dbarea where dname=''"+area+"'' " \
                       ") and cluster_flag=''MINTEMP''') "

            cur.execute(sqlDelete)
            
            sqlDict="select b.temperature,avg(a.aveload) from "+currentSchema+".load_daytrait a,"+currentSchema+".autovalue b where DATE(a.mdate)=DATE(b.time) " \
                    "and a.area=(select area from "+currentSchema+".dbarea where dname='"+area+"') and b.stcd = (select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
                    "and date(a.mdate) in ( " \
                    "select  date from " \
                    "(" \
                    "SELECT Date(time) date,min(temperature) temperature " \
                    "from "+currentSchema+".autovalue " \
                    "where " \
                    "to_char(time, 'yyyy') ='"+mdate+"' and " \
                    "stcd = (select stcd  from "+currentSchema+".qstation  where stnm = '"+area+"' ) group by Date(time)  " \
                    "having max(temperature)<(SELECT (weather_sens+1)*10 FROM "+currentSchema+".load_weather_sens where Dname='"+area+"' and myear='"+mdate+"' and weather_type='MINTEMP') " \
                    "order by Date(time) " \
                    ") a) " \
                    "and b.temperature in(select distinct temperature from " \
                    "( " \
                    "SELECT Date(time) date,min (temperature) temperature " \
                    "from "+currentSchema+".autovalue where  to_char(time, 'yyyy') ='"+mdate+"' and  stcd = (select stcd " \
                    "from "+currentSchema+".qstation where stnm = '"+area+"' )  group by Date(time)" \
                    "having max(temperature)<(SELECT (weather_sens+1)*10 FROM "+currentSchema+".load_weather_sens where Dname='"+area+"' and myear='"+mdate+"' and weather_type='MINTEMP') " \
                    " order by Date(time) " \
                    ") b ) " \
                    "group by b.temperature order by b.temperature asc "
            start = time.time()
            dict={} #产生温度
            cur.execute(sqlDict)
            tempAndLoad=cur.fetchall()
	    #接受温度和平均负荷平均值差的字典
            rowDataTmp = list_2d = [[0 for col in xrange(2)] for row in xrange(len(tempAndLoad)-1)]
            index=0
            while index<(len(tempAndLoad)-1):
                rowDataTmp[index][0]=tempAndLoad[index][0] #是为了将温度值赋予给列表
                #将计算后的差赋予给列表，温度是由高到低，因为是下比，所以温度低处的值减温度高处的值
                rowDataTmp[index][1]=float(tempAndLoad[index][1])-float(tempAndLoad[index+1][1])
                index+=1
            for row in rowDataTmp:
                dict[row[0]]=row[1]
            sql1="select a.area,a.mdate,min(b.temperature),a.maxload,a.aveload from "+currentSchema+".load_daytrait a,"+currentSchema+".autovalue b " \
                 "where DATE(a.mdate)=DATE(b.time) and a.area=(select area from "+currentSchema+".dbarea where dname='"+area+"') "\
                 "and b.stcd = (select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
                 "and DATE(a.mdate) in  " \
                 "(SELECT DATE(time) from "+currentSchema+".autovalue where to_char(time, 'yyyy') ='"+mdate+"' and " \
                 "stcd = (select stcd from "+currentSchema+".qstation  where stnm = '"+area+"' )  " \
                 " group by DATE(time)   " \
                 "having max(temperature)<(SELECT weather_sens*10 FROM "+currentSchema+".load_weather_sens where Dname='"+area+"' and myear='"+mdate+"' and weather_type='MINTEMP') " \
                 "ORDER BY DATE(time) ) "\
                 "group by a.area,a.mdate,a.maxload,a.aveload " \
                 "having max(b.temperature)<(SELECT weather_sens*10  FROM "+currentSchema+".load_weather_sens where Dname='"+area+"' and myear='"+mdate+"' and weather_type='MINTEMP') "\
                 "order by a.mdate desc"
            cur.execute(sql1)
            rowdata=cur.fetchall()
            list_2d = [[0 for col in xrange(2)] for row in xrange(len(rowdata))]
            for index in xrange(len(rowdata)):
                if(dict.has_key(rowdata[index][2])):
                      list_2d[index][0]=rowdata[index][2]
                      list_2d[index][1]=dict[rowdata[index][2]] #将字典中拿到的最高负荷平均值差值插入到待聚类数据集中
            typedata=list_2d
            dataMat=mat(typedata)
            dataSet=mat(dataMat[:,0:]) #取出所有数据从第0列起初所有数据


    #zeros参数中第一个参数为矩阵行，第二个参数为矩阵列数
    #mat函数可以将目标数据的类型转换为矩阵（matrix）
            k=4
            m = shape(dataSet)[0]

            ClustDist=mat(zeros((m,2))) #zeros((2, 1))#生成2行1列的零矩阵
            clustercents=randCenters(dataSet,k) #随机生成聚类中心
            flag=True;

    #===算法停止条件
            while flag:
                flag=False
            for i in xrange(m): #根据start与stop指定的范围以及step设定的步长，生成一个序列。
                distlist=[distEclud(clustercents[j,:],dataSet[i,:]) for j in range(k)] #此处欧式距离计算出错
                minDist=min(distlist)
                minIndex=distlist.index(minDist)

                if ClustDist[i,0]!=minIndex:
                    flag=True
                    ClustDist[i,:]=minIndex,minDist

                #第四阶段 内循环2遍历每个聚类中心
            for cent in xrange(k):
                ptsInClust=dataSet[nonzero(ClustDist[:,0].A==cent)[0]]
                clustercents[cent,:]=mean(ptsInClust,axis=0)
    #第五阶段 分类结果可视化
            list_3d = [[[0 for col in xrange(7)] for row in xrange(1)]for row in xrange(4)]
            for i in xrange(4):
                list_3d[i] = [[0 for col in xrange(7)] for row in xrange(list(ClustDist[:,0:1]).count(i)*3)]
            cluster1=cluster()
	    if(len(rowdata)>0 and len(dataMat)>0 and len(dataSet)>0  ):
	        cluster1.color_cluster(list_3d,rowdata,dataMat,ClustDist[:,0:1],dataSet)
	    	if(list(ClustDist[:,0:1]).count(0)>0):
                   cur.execute(insertSql(list_3d[0])[:-1])
            	if(list(ClustDist[:,0:1]).count(1)>0):
                   cur.execute(insertSql(list_3d[1])[:-1])
            	if(list(ClustDist[:,0:1]).count(2)>0):
                   cur.execute(insertSql(list_3d[2])[:-1])
            	if(list(ClustDist[:,0:1]).count(3)>0):
                   cur.execute(insertSql(list_3d[3])[:-1])

            end=time.time()
        except BaseException as e:
            s=sys.exc_info()
            logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))

        except psycopg2.DatabaseError as e:
            s=sys.exc_info()
            logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
        finally:
            
            conn.close()


    def color_cluster(self,list_3d,rowdata,dataMat,dataindex,dataSet,k=4):
        index=0
        datalen=len(dataindex)
        count0=count1=count2=count3=0
        n=shape(dataMat)[1] #shape函数是numpy.core.fromnumeric中的函数，它的功能是查看矩阵或者数组的维数。

        for indx in range(datalen):
            if int(dataindex[indx])==0:
                nowtime0=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
		list_3d[0][count0][0:2]=rowdata[index][0:2]
		list_3d[0][count0][2]="temperature" #当天最高温度
                list_3d[0][count0][3]=rowdata[index][2]
                list_3d[0][count0][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime0]
                count0+=1
                #======================
                list_3d[0][count0][0:2]=rowdata[index][0:2]
                list_3d[0][count0][2]="maxload"#当天最高负荷
                list_3d[0][count0][3]=rowdata[index][3]
                list_3d[0][count0][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime0]
                count0+=1
                #=====================
                list_3d[0][count0][0:2]=rowdata[index][0:2]
                list_3d[0][count0][2]="aveload"#当天平均负荷
                list_3d[0][count0][3]=rowdata[index][4]
                list_3d[0][count0][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime0]
                count0+=1

            elif int(dataindex[indx])==1:
                nowtime1=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
                list_3d[1][count1][0:2]=rowdata[index][0:2]
                list_3d[1][count1][2]="temperature" #当天最高温度
                list_3d[1][count1][3]=rowdata[index][2]
                list_3d[1][count1][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime1]
                count1+=1
                #======================
                list_3d[1][count1][0:2]=rowdata[index][0:2]
                list_3d[1][count1][2]="maxload"#当天最高负荷
                list_3d[1][count1][3]=rowdata[index][3]
                list_3d[1][count1][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime1]
                count1+=1
                #=====================
                list_3d[1][count1][0:2]=rowdata[index][0:2]
                list_3d[1][count1][2]="aveload"#当天平均负荷
                list_3d[1][count1][3]=rowdata[index][4]
                list_3d[1][count1][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime1]
                count1+=1;
            elif int(dataindex[indx])==2:
                nowtime2=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
                list_3d[2][count2][0:2]=rowdata[index][0:2]
                list_3d[2][count2][2]="temperature" #当天最高温度
                list_3d[2][count2][3]=rowdata[index][2]
                list_3d[2][count2][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime2]
                count2+=1
                #======================
                list_3d[2][count2][0:2]=rowdata[index][0:2]
                list_3d[2][count2][2]="maxload"#当天最高负荷
                list_3d[2][count2][3]=rowdata[index][3]
                list_3d[2][count2][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime2]
                count2+=1
                #=====================
                list_3d[2][count2][0:2]=rowdata[index][0:2]
                list_3d[2][count2][2]="aveload"#当天平均负荷
                list_3d[2][count2][3]=rowdata[index][4]
                list_3d[2][count2][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime2]
                count2+=1
            elif int(dataindex[indx])==3:
                nowtime3=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
                list_3d[3][count3][0:2]=rowdata[index][0:2]
                list_3d[3][count3][2]="temperature" #当天最高温度
                list_3d[3][count3][3]=rowdata[index][2]
                list_3d[3][count3][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime3]
                count3+=1
                #======================
                list_3d[3][count3][0:2]=rowdata[index][0:2]
                list_3d[3][count3][2]="maxload"#当天最高负荷
                list_3d[3][count3][3]=rowdata[index][3]
                list_3d[3][count3][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime3]
                count3+=1
                #=====================
                list_3d[3][count3][0:2]=rowdata[index][0:2]
                list_3d[3][count3][2]="aveload"#当天平均负荷
                list_3d[3][count3][3]=rowdata[index][4]
                list_3d[3][count3][4:7]=[int(dataindex[indx]),"MINTEMP",nowtime3]
                count3+=1;
            index+=1 #此处的index属于elif范围之外





