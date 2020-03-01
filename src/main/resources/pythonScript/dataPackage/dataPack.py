#-*- coding:utf-8 -*-
import sys
import copy


#dict要格式化的字典，index下标开始写0，DateArray时间数组，最下层的values
def packMap(dict,index,dateArray,data):
    dictTemp={}
    if(index+1!=len(dateArray)):
        key=dateArray[index]
        value=dateArray[index+1]
        if(dict.has_key(key)!=True):
            dict[key]=packMap(dictTemp,index+1,dateArray,data)
        elif(dict.has_key(key)):
            if(dict[key].has_key(value) ):
                dictTemp2={}
                dictTemp2=dict[key]
                dict[key]=packMap(dictTemp2,index+1,dateArray,data)
            else:
                dictTemp3={}
                dictTemp3=dict[key]
                dict[key]=packMap(dictTemp3,index+1,dateArray,data)
    else:
        dict[dateArray[index]]=data
    return dict

#dict为横向，dict2为基准点字典，即dict2为核对依据，返回的是核对后的dict的数据
def compareMap(dict1,dict2): #返回元组
    tempDict={}#dict1的数据规范后
    tempDict2={}#dict2的数据规范后
    for key in dict1.keys():
        if(dict2.has_key(key)):
            if(str(dict1[key]).find("{")!=-1):
                dictValue=dict1[key]
                for key2 in dictValue.keys():
                    if(dict2[key].has_key(key2 ) ):
                        tuptemp=compareMap(dictValue,dict2[key])
                        tempDict[key]=tuptemp[0]
                        tempDict2[key]=tuptemp[1]
            else:
                if(dict2.has_key(key)): #此处的key为dict1的最后一层key了
                    pointValue=[]
                    for point in dict2[key].keys():
                        pointValue.append(dict1[key][int(point)])
                    tempDict[key]=pointValue
                    tempDict2[key]=dict2[key].values()
    return tempDict,tempDict2

#传入一个列表的日期，然后将列表的日期转化为sql可查的括号中字符串
def transBracket(dates):
    temp="("
    for data in dates:
        temp+="'"+str(data)+"',"
    return temp[:-1]+")"

#传入一个列表的日期，然后将列表的日期转化为sql可查的括号中字符串
def transForDelete(dates):
    temp="("
    for data in dates:
        temp+="''"+str(data)+"'',"
    return temp[:-1]+")"










