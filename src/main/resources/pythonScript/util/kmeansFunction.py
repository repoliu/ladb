# -*- coding: UTF-8 -*-
from numpy import *
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
