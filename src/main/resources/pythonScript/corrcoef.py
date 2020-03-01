# -*- coding: utf-8 -*-
from math import sqrt
import sys


def multipl(a,b):
    sumofab=0.0
    for i in range(len(a)):
        temp=a[i]*b[i]
        sumofab+=temp
    return sumofab

def corrcoef(x,y):
    x1=[0 for col in xrange(len(x))]
    y1=[0 for col in xrange(len(y))]
    for index in xrange(len(x)):
        if(x[index]==None):
            x[index]=0
        if(y[index]==None):
            y[index]=0
        x1[index]=float(x[index])
        y1[index]=float(y[index])
    n=len(x1)
    #求和
    sum1=sum(x1)
    sum2=sum(y1) #除数不能为0，判断为0时输出相关性0
    if sum2==0:
        return  0
    #求乘积之和
    sumofxy=multipl(x1,y1)
    #求平方和
    sumofx2 = sum([pow(i,2) for i in x1])
    sumofy2 = sum([pow(j,2) for j in y1])
    num=sumofxy-(float(sum1)*float(sum2)/n)
    #计算皮尔逊相关系数
    den=sqrt((sumofx2-float(sum1**2)/n)*(sumofy2-float(sum2**2)/n))
    if den==0:
        return 0
    return (num/den)*100
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

