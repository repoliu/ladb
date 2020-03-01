# -*- coding: UTF-8 -*-

#用于参与聚类的数字数据的过滤
def numberFilter(data,start,end):
    list_2d = [[0 for col in xrange(end-start+1)] for row in xrange(len(data))]
    for index in xrange(len(data)):
        for index2 in xrange(start,end):
            if(data[index][index2]!=None):
                list_2d[index][index2-start]=float(data[index][index2])
            else:
                list_2d[index][index2-start]=float(0)
    return list_2d

#用于参与不聚类的字段数据的过滤
def fieldFilter(data,start,end):
    list_2dSeven = [[0 for col in xrange(end-start)] for row in xrange(len(data))]
    for index in xrange(len(data)):
        for index2 in xrange(start,end):
            if(data[index][index2]!=None):
                list_2dSeven[index][index2]=data[index][index2]
            else:
                list_2dSeven[index][index2]="NULL"
    return list_2dSeven