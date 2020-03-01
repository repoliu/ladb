# -*- coding: UTF-8 -*-


import time;

from util.log import *
from util.readProperties import *
from util.dataFilter import *
from util.kmeansFunction import *
import psycopg2
from numpy import *
import sys
reload(sys)
sys.setdefaultencoding('utf-8')


script_path = os.path.realpath(__file__) #脚本绝对路径
script_dir = os.path.dirname(script_path)#脚本所在目录

#要严格注意空格

arr=[];

def color_cluster(dataindex,dataSet,k=4):
    index=0
    datalen=len(dataindex)
    count0=count1=count2=count3=0
    n=shape(dataMat)[1] #shape函数是numpy.core.fromnumeric中的函数，它的功能是查看矩阵或者数组的维数。
    arr0 = [[0 for col in xrange(298)] for row in xrange(list(dataindex).count(0))]
    arr1 = [[0 for col in xrange(298)] for row in xrange(list(dataindex).count(1))]
    arr2 = [[0 for col in xrange(298)] for row in xrange(list(dataindex).count(2))]
    arr3 = [[0 for col in xrange(298)] for row in xrange(list(dataindex).count(3))]
    nowDate=time.strftime('%Y-%m-%d',time.localtime(time.time()))
    for indx in range(datalen):
        if int(dataindex[indx])==0:
            list_3d[0][count0][0:7]=list_2dSeven[indx][0:7]
            list_3d[0][count0][7:296]=typedata[indx]
            list_3d[0][count0][296]=nowDate
            list_3d[0][count0][297]=0
            count0+=1
        elif int(dataindex[indx])==1:
            list_3d[1][count1][0:7]=list_2dSeven[indx][0:7]
            list_3d[1][count1][7:296]=typedata[indx]
            list_3d[1][count1][296]=nowDate
            list_3d[1][count1][297]=1
            count1+=1

        elif int(dataindex[indx])==2:
            list_3d[2][count2][0:7]=list_2dSeven[indx][0:7]
            list_3d[2][count2][7:296]=typedata[indx]
            list_3d[2][count2][296]=nowDate
            list_3d[2][count2][297]=2
            count2+=1

        elif int(dataindex[indx])==3:
            list_3d[3][count3][0:7]=list_2dSeven[indx][0:7]
            list_3d[3][count3][7:296]=typedata[indx]
            list_3d[3][count3][296]=nowDate
            list_3d[3][count3][297]=3
            count3+=1

        index+=1 #此处的index属于elif范围之外

def producePoint(num):
    array=[]
    for x in xrange(num):
        if(x<10):
            array.insert(x,"f0"+str(x))
        else:
            array.insert(x,"f"+str(x))
    return  array

def insertSql(arrData):

    sql2="insert into "+currentSchema+"."+clusterTable+"(mdate,id,code,descr,dv_id,source,flag,"
    for data0 in producePoint(289):
        sql2+=data0+","
    sql2+="ver_time,cluster_type) values"
    for data1 in arrData:
        sql2+="('"+str(data1[0])+"',"+str(data1[1])+","+str(data1[2])+"," \
                "'"+str(data1[3])+"',"+str(data1[4])+",'"+str(data1[5])+"','"+str(data1[6])+"',  "
        for data2 in data1[7:296]:
            sql2+=""+str(data2)+","
        sql2+="'"+str(data1[296])+"',"+str(data1[297])+"),"
    return sql2


#=================聚类主程序
province=sys.argv[1].decode('GBK').encode('utf-8')
mdate=sys.argv[2]

# province="福建"
# mdate="20140101"
table="bus_hisdata"+mdate[0:4]
clusterTable="bus_hisdata"+mdate[0:4]+"_cluster"
typedata=[]

if __name__ == '__main__':
    start=time.time()
    dict=readDb2Properties()
    currentSchema=dict['currentSchema']
    conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
    cur = conn.cursor()
    conn.set_session(autocommit=True)
    try:

        sqlDelete="select "+currentSchema+".p_delete('"+dict['currentSchema']+"','"+clusterTable+"','mdate="+mdate+" and  substring(DESCR,0,position(''.'' in DESCR) )=''"+province+"''')"
        cur.execute(sqlDelete)
        
        sql1="SELECT * FROM "+currentSchema+"."+table+" where mdate='"+mdate+"' and source='ss' " \
             "and flag='w' and  substring(DESCR,0,position('.' in DESCR) )='"+province+"' "
        cur.execute(sql1)
        rowdata=cur.fetchall()

        list_2d = [[0 for col in xrange(289)] for row in xrange(len(rowdata))]
        typedata=numberFilter(rowdata,7,296)
        list_2dSeven=fieldFilter(rowdata,0,7)
        dataMat=mat(typedata)
        dataSet=mat(dataMat[:,0:]) #取出所有数据从第0列起初所有数据

        #zeros参数中第一个参数为矩阵行，第二个参数为矩阵列数
        #mat函数可以将目标数据的类型转换为矩阵（matrix）
        k=4
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
        list_3d = [[[0 for col in xrange(298)] for row in xrange(1)]for row in xrange(4)]
        for i in xrange(4):
            list_3d[i] = [[0 for col in xrange(298)] for row in xrange(list(ClustDist[:,0:1]).count(i))]
        color_cluster(ClustDist[:,0:1],dataSet)
        if(list(ClustDist[:,0:1]).count(0)>0):
            cur.execute(insertSql(list_3d[0])[:-1])
            
        if(list(ClustDist[:,0:1]).count(1)>0):
            cur.execute(insertSql(list_3d[1])[:-1])
            
        if(list(ClustDist[:,0:1]).count(2)>0):
            cur.execute(insertSql(list_3d[2])[:-1])
            
        if(list(ClustDist[:,0:1]).count(3)>0):
            cur.execute(insertSql(list_3d[3])[:-1])
            
        end=time.time()
        print True
    except BaseException as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    finally:
        
        conn.close()



