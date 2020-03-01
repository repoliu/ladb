# -*- coding: utf-8 -*-
import sys
import compileall
import numpy as np
#from util.readProperties import *
import math
import time
import os

#绝对值
#print abs(1221.3)

# for i in range(1, len(sys.argv)):
#     province=sys.argv[1]
#
#
# sql1="SELECT * FROM bus_hisdata2014 where mdate=20140101 and " \
#      "source='ss' and flag='w' and  MID(DESCR,0,INSTR(DESCR,'.')-1)='福建'"
# sql2="SELECT * FROM bus_hisdata2014 where mdate=20140101 and source='ss' " \
#      "and flag='w' and  MID(DESCR,0,INSTR(DESCR,'.')-1)='"+province+"'"
#
# print "sql1:",sql1
# print "sql2:",sql2

#输出系统当前时间
# print time.strftime('%Y-%m-%d',time.localtime(time.time()))
# print time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))

# list_3d = [[[0 for col in xrange(2)] for row in xrange(1)]for row in xrange(4)]
# Decimal(222)
# print Decimal("%.4f" % float(-18.4200))+Decimal("%.4f" % float(-18.4200))
#尽管显示的乱码，但是拼接到sql时可正常查询
# area=sys.argv[1].decode('GBK').encode('utf-8')
# mdate=sys.argv[2].decode('GBK').encode('utf-8')




#字符串转格式化时间
# print time.strftime("%Y-%m-%d %H:%M:%S", time.strptime('2015-01-01 00:00:00', "%Y-%m-%d %H:%M:%S"))
# print time.strptime('2015-01-01 00:00:00', "%Y-%m-%d %H:%M:%S").tm_hour
# dict=readDb1Properties()
# print dict
def producePoint(num):
    array=[]

    for x in xrange(1,num):
        array.insert(x,"F"+str(x*6)+"0")
    return  array
print producePoint(25)
# try:
#     1 / 0
# except Exception as e:
#     print e
#     '''异常的父类，可以捕获所有的异常'''
#     print "0不能被除"
# else:
#     '''保护不抛出异常的代码'''
#     print "没有异常"
# finally:
#     print "最后总是要执行我"


def memCheck():
    info = psutil.virtual_memory()
    #最原始的输出单位字字节B
    startMem=psutil.Process(os.getpid()).memory_info().rss/1024
    print time.strftime("%Y-%m-%d", time.strptime('20150101', "%Y%m%d"))
    endMem=psutil.Process(os.getpid()).memory_info().rss/1024
    print endMem-startMem,"KB"


def timecheck():
    start=time.time()
    memCheck()
    end = time.time()
    print end-start
    #compileall.compile_dir(r'G:\IDEA\192.168.9.110\ladb\src\main\resources\pythonScript')
temperatureChange=[ [2, 2, 2, 3, 2, 2, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 4, 2, 2, 3, 2, 4, 2],
                    [3602.223, 3438.673, 3259.918, 3172.075, 3108.502, 3177.894, 3381.473,
                     3864.118, 4286.126, 4488.781, 4618.183, 4846.471, 4412.561, 4254.726,
                     4184.874, 4203.096, 4425.653, 4681.053, 4840.458, 4707.442, 4580.916,
                     4451.059, 4188.804]]
