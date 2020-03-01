# -*- coding: UTF-8 -*-


import sys
import os
import datetime
import time
from util.readProperties import *
from numpy import *
import psycopg2
from util.log import *
reload(sys)
sys.setdefaultencoding('utf-8')


script_path = os.path.realpath(__file__) #脚本绝对路径
script_dir = os.path.dirname(script_path)#脚本所在目录

#要严格注意空格


def file2matrix(path,delimiter):
    recordlist=[]
    fp=open(path,"rb")
    content=fp.read()
    fp.close()
    rowlist=content.splitlines()#按行转换为一维表
    recordlist=[map(eval,row.split(delimiter)) for row in rowlist if row.strip()]
    return mat(recordlist)


    recordmat=file2matrix(path,"\t")
    shape(recordmat)




arr=[];
noRain_k=0
nowDate=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time())) # 时间
def color_cluster(list_2d,list_2dThree,list_2d_DValue,list_3d,arr0,arr1,arr2,dataindex,dataMat,dataSet,k=4):
    index=0
    datalen=len(dataindex)
    count0=count1=count2=0
    n=shape(dataMat)[1] #shape函数是numpy.core.fromnumeric中的函数，它的功能是查看矩阵或者数组的维数。
    # arr0 = [[0 for col in xrange(104)] for row in xrange(list(dataindex).count(0))]
    # arr1 = [[0 for col in xrange(104)] for row in xrange(list(dataindex).count(1))]
    # arr2 = [[0 for col in xrange(104)] for row in xrange(list(dataindex).count(2))]

    for indx in range(datalen):
        if int(dataindex[indx])==0:
            list_3d[0][count0][0:4]=list_2dThree[indx][0:4]
            list_3d[0][count0][4:100]=list_2d[indx]
            list_3d[0][count0][101]=nowDate
            list_3d[0][count0][102]=0
            list_3d[0][count0][103]=0   #loadvalue_type 0 原始值 1 差值
            list_3d[0][count0][104]=list_2dThree[indx][-1]

            arr0[count0][0:4]=list_2dThree[indx][0:4]
            arr0[count0][4:100]=list_2d_DValue[indx]
            arr0[count0][101]=nowDate
            arr0[count0][102]=0
            arr0[count0][103]=1   #loadvalue_type 0 原始值 1 差值
            arr0[count0][104]=list_2dThree[indx][-1]
            count0+=1
        elif int(dataindex[indx])==1:
            list_3d[1][count1][0:4]=list_2dThree[indx][0:4]
            list_3d[1][count1][4:100]=list_2d[indx]
            list_3d[1][count1][101]=nowDate
            list_3d[1][count1][102]=1
            list_3d[1][count1][103]=0
            list_3d[1][count1][104]=list_2dThree[indx][-1]

            arr1[count1][0:4]=list_2dThree[indx][0:4]
            arr1[count1][4:100]=list_2d_DValue[indx]
            arr1[count1][101]=nowDate
            arr1[count1][102]=1
            arr1[count1][103]=1   #loadvalue_type 0 原始值 1 差值
            arr1[count1][104]=list_2dThree[indx][-1]
            count1+=1
        elif int(dataindex[indx])==2:
            list_3d[2][count2][0:4]=list_2dThree[indx][0:4]
            list_3d[2][count2][4:100]=list_2d[indx]
            list_3d[2][count2][101]=nowDate
            list_3d[2][count2][102]=2
            list_3d[2][count2][103]=0
            list_3d[2][count2][104]=list_2dThree[indx][-1]

            arr2[count2][0:4]=list_2dThree[indx][0:4]
            arr2[count2][4:100]=list_2d_DValue[indx]
            arr2[count2][101]=nowDate
            arr2[count2][102]=2
            arr2[count2][103]=1   #loadvalue_type 0 原始值 1 差值
            arr2[count2][104]=list_2dThree[indx][-1]

            count2+=1

        index+=1 #此处的index属于elif范围之外



#欧式距离公式
def distEclud(vecA,vecB):
    return linalg.norm(vecA-vecB)

def randCenters(dataSet,k):
    n=shape(dataSet)[1] #shape函数是numpy.core.fromnumeric中的函数，它的功能是查看矩阵或者数组的维数。
    clustercents=mat(zeros((k,n)))
    tmp=mat(zeros((1,k)))
    for col in range(n):
        #print "dataSet[:,col]:",dataSet[:,col]
        mincol=min(dataSet[:,col]);maxcol=max(dataSet[:,col]) #min() 方法返回给定参数的最小值，参数可以为序列。
        # tmp[:]=()
        #max() 方法返回给定参数的最小值，参数可以为序列。
        clustercents[:,col]= float(mincol)+float(maxcol - mincol)*random.rand(k,1) #！！！！！重点错误地区

        #random.rand(k,1)产生一个0~1之间的随机数向量：k,1表示k行1列的随机数
    return clustercents

def producePoint(num):
    array=[]
    for x in xrange(num):
        if(x<1):
            array.insert(x,"F00")
        else:
            array.insert(x,"F"+str(x*15))
    return  array

def Point(num):
    array=[]
    for x in xrange(num):
        if(x<10):
            array.insert(x,"F0"+str(x))
        else:
            array.insert(x,"F"+str(x))
    return  array

def insertSql(arrData,type,noRain_k):
    sql2="insert into "+currentSchema+"."+clusterTable+"(area,id,mdate,ddate,"
    for data0 in Point(97):
        sql2+=data0+","
    sql2+="ver_time,cluster_type,loadvalue_type,ndate) values"

    for data1 in arrData:
        sql2+="('"+str(data1[0])+"',"+str(data1[1])+","+str(data1[2])+"," \
               "'"+str(data1[3])+"', "
        if type==1:
            for data2 in data1[4:101]:
                sql2+=""+str(data2)+","
            sql2+="'"+nowDate+"',"+str(noRain_k)+","+str(0)+","+str('null')+"),"
        else:
            for data2 in data1[4:101]:
                sql2+=""+str(data2)+","
            sql2+="'"+str(data1[101])+"',"+str(data1[102])+","+str(data1[103])+","+str(data1[104])+"),"

    return sql2

#=================聚类主程序
# area=sys.argv[1]
# mdate=sys.argv[2]

# area="福州"
# mdate="2015"
dataTable="load_hisdata"
rainTable="load_rain_info"
clusterTable="load_hisdata_96_cluster"
typedata=[]
dict=readDb1Properties()
currentSchema=dict['currentSchema']
conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
cur = conn.cursor()
conn.set_session(autocommit=True)

def start(area,mdate):

    try:

        #查询指定年份中暴雨及以上级别的日期
        sql4="select distinct to_char(time,'yyyymmdd')time from "+currentSchema+"."+rainTable+" where to_char(time,'yyyy')='"+mdate+"' and rain_leve in('大雨','暴雨','大暴雨','特大暴雨') order by time"
        cur.execute(sql4)
        rainstormDay=cur.fetchall()

        #保存需要聚类的日期
        rain_DateDict={}

        #保存用于接收循环里的的各种数据
        list_2d = []
        list_2d_DValue=[]
        list_2dThree = []
        list_noRainFuhe=[]
        #保存降雨日日期
        rainstormList=[]
        for data in list(rainstormDay):
            rain_DateDict[str(list(data)[0])]=str(list(data)[0])
            rainstormList.append(str(list(data)[0]))
        # print ('raindate:',rainstormList)
        noRainDaydict={}#未降雨日日期字典

        #循环查询所有符合暴雨日条件的日期
        for rain_day in rainstormList:
            #查询降雨日前一日未降雨日
            sql="select distinct to_char(time,'yyyymmdd')time from "+currentSchema+"."+rainTable+" where to_char(time,'yyyymmdd')< "+rain_day+" order by time desc"
            cur.execute(sql)
            dateList=cur.fetchall()
            #保存降雨日前的所有降雨的日期
            dateDict={}
            for data in list(dateList):
                dateDict[list(data)[0]]=list(data)[0]

            date_rain = datetime.datetime.strptime(rain_day,'%Y%m%d')#str->date
            for index in xrange(len(dateDict)):
                delta=datetime.timedelta(days=-index-1)
                n_days=date_rain+delta
                n_days=n_days.strftime('%Y%m%d')#date -> str
                if (dateDict.has_key(n_days)):
                    continue
                else:
                    date_noRain=n_days
                    break
            #保存未降雨日日期
            noRainList=[]
            if date_noRain not in rain_DateDict:
                rain_DateDict[date_noRain]=date_noRain
            noRainList.append(date_noRain)

            # print ('rain_day:',rain_day)
            # print ('noRain:',noRainList)
            #查询降雨日后近7天暴雨一下级别的日期
            sql2="select distinct to_char(time,'yyyymmdd')time from  "+currentSchema+"."+rainTable+"  where " \
                 "rain_leve in ('小雨','中雨') and to_char(time,'yyyymmdd') > '"+rain_day+"' and areaname='"+area+"' order by time limit 7"
            cur.execute(sql2)
            date_List=cur.fetchall()
            #保存 降雨日后7天日期
            RainSevenList=[]
            for data in date_List:
                if str(list(data)[0]) not in rain_DateDict:
                    rain_DateDict[str(list(data)[0])]=str(list(data)[0])
                    RainSevenList.append(str(list(data)[0]))
            # print ('RainSeven:',RainSevenList)

            # 查询96点负荷数据
            sql3="SELECT distinct area,id,mdate,ddate"
            for data in producePoint(97):
                sql3+=","+data
            sql3+=" FROM "+currentSchema+"."+dataTable+" where  AREA=(select area from "+currentSchema+".dbarea where dname='"+area+"') and mdate in ("
            sql3+="'"+date_noRain+"','"+rain_day+"'"
            for data in RainSevenList:
                sql3+=",'"+data+"'"
            sql3+=" ) order by mdate"

            cur.execute(sql3)
            rowdata=cur.fetchall()

            #未降雨日负荷数据
            list_noRain = [[0 for col in xrange(104)] for row in xrange(1)]
            for index in xrange(101):
                list_noRain[0][index]=rowdata[0][index]
            # if list_noRain[0][2] not in dict:
            #     dict[list_noRain[0][2]]=list_noRain[0][2]
            #     list_noRainFuhe+=list_noRain
            if noRainDaydict.has_key(list_noRain[0][2]):
                i=0
            else:
                noRainDaydict[list_noRain[0][2]]=list_noRain[0][2]
                list_noRainFuhe+=list_noRain

            # if str(list_noRain[0][2]) not in list_noRainFuhe:
            #     list_noRainFuhe[str(list(list_noRain)[0][2])]=list_noRain

            #降雨日负荷数据
            list_fuhe = [[0 for col in xrange(97)] for row in xrange(len(rowdata)-1)]
            #未降雨日与降雨日负荷差值数据
            list_tmp = [[0 for col in xrange(97)] for row in xrange(len(rowdata)-1)]
            for index in xrange(len(rowdata)-1):
                for index2 in xrange(4,101):
                    if(rowdata[index][index2]!=None ):
                        list_fuhe[index][index2-4]=float(rowdata[index+1][index2])
                        list_tmp[index][index2-4]=abs(float(rowdata[0][index2])-float(rowdata[index+1][index2]))
                    else:
                        list_fuhe[index][index2-4]=float(0)
                        list_tmp[index][index2-4]=float(0)



            # list_tmp = [[0 for col in xrange(97)] for row in xrange(len(rowdata)-1)]
            # for index in xrange(len(rowdata)-1):
            #     for index2 in xrange(4,101):
            #         if(rowdata[index][index2]!=None):
            #             list_tmp[index][index2-4]=float(rowdata[0][index2])-float(rowdata[index+1][index2])
            #         else:
            #             list_tmp[index][index2-4]=float(0)


            #拼接保存负荷数据
            list_2d+=list_fuhe
            list_2d_DValue+=list_tmp

            #降雨日地点日期信息
            list_riqi = [[0 for col in xrange(5)] for row in xrange(len(rowdata)-1)]
            for index in xrange(len(rowdata)-1):
                for index2 in xrange(0,4):
                    if(rowdata[index][index2]!=None):
                        list_riqi[index][index2]=rowdata[index+1][index2]
                    else:
                        list_riqi[index][index2]="NULL"
                #添加未降雨日到list_fuhe ,list_tmp 中
                list_riqi[index][4]=noRainList[0]
            # list_tmp2=[[0 for col in xrange(4)] for row in xrange(len(list_riqi)-1)]
            # for index in xrange(len(list_riqi)-1):
            #     list_tmp2[index]=list_riqi[index+1]
            list_2dThree+=list_riqi


        # print ('rain_DateDict:',rain_DateDict)
        # print list_noRainFuhe
        # print list_2dThree
        # print list_2d
        # print list_2d_DValue


        #删除数据
        condition="mdate in ("
        for data in rain_DateDict:
            condition+="''"+data+"'',"
        condition=condition[:-1]
        condition+=" ) and  AREA=(select area from "+currentSchema+".dbarea where dname=''"+area+"'')"
        sqlDelete="select "+currentSchema+".p_delete('"+dict['currentSchema']+"','"+clusterTable+"','"+condition+"')"
        if(len(rain_DateDict)>0):
            cur.execute(sqlDelete)
            
        typedata=list_2d_DValue


        dataMat=mat(typedata)
        dataSet=mat(dataMat[:,0:]) #取出所有数据从第0列起初所有数据

        #zeros参数中第一个参数为矩阵行，第二个参数为矩阵列数
        #mat函数可以将目标数据的类型转换为矩阵（matrix）
        k=3
        noRain_k=bytes(k)
        m = shape(dataSet)[0]
        ClustDist=mat(zeros((m,2))) #zeros((2, 1))#生成2行1列的零矩阵
        clustercents=randCenters(dataSet,k) #随机生成聚类中心

        flag=True;
        counter=[];

        #===算法停止条件
        while flag:
            flag=False

        #X[:,0]是numpy中数组的一种写法，表示对一个二维数组，取该二维数组第一维中的所有数据，
        #第二维中取第0个数据，直观来说，X[:,0]就是取所有行的第0个数据, X[:,1] 就是取所有行的第1个数据
        #X[n,:]是取第1维中下标为n的元素的所有值。
        #第三阶段内循环一遍DataSet数据集

        for i in xrange(m): #根据start与stop指定的范围以及step设定的步长，生成一个序列。
            distlist=[distEclud(clustercents[j,:],dataSet[i,:]) for j in range(k)] #此处欧式距离计算出错
            minDist=min(distlist)
            minIndex=distlist.index(minDist)
            if ClustDist[i,0]!=minIndex:
                flag=True
                ClustDist[i,:]=minIndex,minDist
                #第四阶段 内循环2遍历每个聚类中心
        for cent in xrange(k):
            #nonzeros(a)返回数组a中值不为零的元素的下标，它的返回值是一个长度为a.ndim(数组a的轴数)的元组，元组的每个元素都是一个整数数组，
            #其值为非零元素的下标在对应轴上的值。例如对于一维布尔数组b1，nonzero(b1)所得到的是一个长度为1的元组，
            #它表示b1[0]和b1[2]的值不为0(False)。
            #ClustDist的取值出错，排错
            ptsInClust=dataSet[nonzero(ClustDist[:,0].A==cent)[0]]
            clustercents[cent,:]=mean(ptsInClust,axis=0)

        #第五阶段 分类结果可视化
        list_3d = [[[0 for col in xrange(105)] for row in xrange(1)]for row in xrange(3)]
        list_3d_DValue=[]

        arr0 = [[0 for col in xrange(105)] for row in xrange(list(ClustDist[:,0:1]).count(0))]
        arr1 = [[0 for col in xrange(105)] for row in xrange(list(ClustDist[:,0:1]).count(1))]
        arr2 = [[0 for col in xrange(105)] for row in xrange(list(ClustDist[:,0:1]).count(2))]
        for i in xrange(3):
            list_3d[i] = [[0 for col in xrange(104)] for row in xrange(list(ClustDist[:,0:1]).count(i))]

        color_cluster(list_2d,list_2dThree,list_2d_DValue,list_3d,arr0,arr1,arr2,ClustDist[:,0:1],dataMat,dataSet)

        if(list(ClustDist[:,0:1]).count(0)>0):
            cur.execute(insertSql(list_3d[0],0,0)[:-1])
            cur.execute(insertSql(arr0,0,0)[:-1])
        if(list(ClustDist[:,0:1]).count(1)>0):
            cur.execute(insertSql(list_3d[1],0,0)[:-1])
            cur.execute(insertSql(arr1,0,0)[:-1])
        if(list(ClustDist[:,0:1]).count(2)>0):
            cur.execute(insertSql(list_3d[2],0,0)[:-1])
            cur.execute(insertSql(arr2,0,0)[:-1])

        #插入未降雨日数据
        if(len(list_noRainFuhe)>0):
            cur.execute(insertSql(list_noRainFuhe,1,noRain_k)[:-1])

        # print True
    except BaseException as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))

    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    finally:
        
        conn.close()



