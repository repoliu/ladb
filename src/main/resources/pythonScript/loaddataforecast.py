# -*- coding: UTF-8 -*-
import sys
import psycopg2
from util.readProperties import *
from util.bpNeuralNetworks import *
from math import isnan
import time
from util.log import *
reload(sys)
sys.setdefaultencoding('utf-8')
# area="57"
# mdate="20150808"
# tempValChange=0
# rainValChange=0
# windValChange=0
area = sys.argv[1].decode('GBK').encode('utf-8')
mdate = sys.argv[2]
# 接受前台传过来的变化的值,可正可负
tempValChange = int(sys.argv[3]) * 10
rainValChange = sys.argv[4]
windValChange = sys.argv[5]
dict = readDb1Properties()
currentSchema = dict['currentSchema']
conn = psycopg2.connect(database=dict['database'], user=dict['user'], password=dict['passwd'], host=dict['host'],
                        port=dict['port'])
cur = conn.cursor()

# 天气数据
weathercomparedata = [[[0 for col in xrange(24)] for col in xrange(3)] for row in xrange(2)]
try:

    stcdSql = "select stcd from " + currentSchema + ".qstation where stnm=(select dname from " + currentSchema + ".dbarea where area='" + area + "') "
    cur.execute(stcdSql)
    stcd = str(cur.fetchall()[0][0])
    sqlDate = "select  distinct mdate from " + currentSchema + ".relation_support where area=" + area + " and " \
              "relation_type in('大雨-风力','大雨-降雨量','暴雨-风力','暴雨-降雨量','大暴雨-风力','大暴雨-降雨量','特大暴雨-风力','特大暴雨-降雨量') and mdate<=" + mdate + " " \
              "and substring(mdate,0,5)='" + mdate[0:4] + "' and relation_degree>=20 order by mdate"
    # and mdate>20150930
    cur.execute(sqlDate)
    dates = cur.fetchall()
    if len(dates) == 0:
        output = "查无数据"
        print str(output.decode('UTF-8'))
        sys.exit(0)

    # 括号中时间条件，不带横线
    timeConditionNoLine = "("
    # 括号中时间条件，带横线
    timeConditionLine = "("
    # 时间条件封装
    for date in dates:
        timeConditionNoLine += "'" + str(date[0]) + "',"
        timeConditionLine += "'" + time.strftime("%Y-%m-%d", time.strptime(str(date[0]), "%Y%m%d")) + "',"
    timeTempNoline = timeConditionNoLine[:-1] + ")"
    timeTempline = timeConditionLine[:-1] + ")"
    # 查出天气每日存在哪些时间点，作为字典使用，用于与负荷点核对
    sqlDict = "select  distinct to_char(time, 'yyyy-mm-dd') as time,to_char(time, 'hh24') " \
              " from " + currentSchema + ".autovalue where stcd=" + stcd + " and date(time) in " + timeTempline + " order by time"
    cur.execute(sqlDict)
    existTime = cur.fetchall()
    timeDict = {}
    for data in existTime:
        if timeDict.has_key(data[0]):
            timeDict[data[0]].append(int(data[1]))
        else:
            timeDict[data[0]] = []
            timeDict[data[0]].append(int(data[1]))
    sql0 = "select distinct time,temperature,rain,wp from " + currentSchema + ".autovalue where stcd=" + stcd + " and date(time) in " + timeTempline + " order by time"
    cur.execute(sql0)
    weatherdata = cur.fetchall()
    # 改变前的值
    temperature = [[] for col in xrange(len(dates))]
    rain = [[] for col in xrange(len(dates))]
    wind = [[] for col in xrange(len(dates))]
    # 保存的24个点的数据，需要接受改变后的值，即各个时间点的数值要与前台传过来的值做加减计算
    temperatureChange = [[] for col in xrange(len(dates))]
    rainChange = [[] for col in xrange(len(dates))]
    windChange = [[] for col in xrange(len(dates))]

    index = 0

    for i in xrange(len(weatherdata)):
        if i > 1 and str(weatherdata[i][0])[0:10] != str(weatherdata[i - 1][0])[0:10]:
            index += 1
        temperature[index].append(weatherdata[i][1])
        rain[index].append(weatherdata[i][2])
        wind[index].append(weatherdata[i][3])
        temperatureChange[index].append(weatherdata[i][1] + int(tempValChange))
        if (weatherdata[i][2] + int(rainValChange) < 0):
            rainChange[index].append(0)
        else:
            rainChange[index].append(weatherdata[i][2] + int(rainValChange))
        if (weatherdata[i][3] + int(windValChange) < 0):
            windChange[index].append(0)
        else:
            windChange[index].append(weatherdata[i][3] + int(windValChange))

    sql1 = "select dtistinct to_char(date(mdate),'yyyy-mm-dd') as mdate,f00,F60,F120,F180,F240,F300,F360,F420,F480,F540,F600,F660,F720," \
           "F780,F840,F900,F960,F1020,F1080,F1140,F1200,F1260,F1320,F1380 " \
           "from " + currentSchema + ".load_hisdata where area=" + area + " and mdate in " + timeTempNoline + " order by mdate"
    cur.execute(sql1)
    loaddata = cur.fetchall()
    rowMapdata = {}
    # 负荷
    loadfloatdata = [[] for row in xrange(np.shape(loaddata)[0])]
    for index in xrange(len(loaddata)):
        for index2 in sorted(timeDict[loaddata[index][0]]):
            if (loaddata[index][index2 + 1] != None):
                loadfloatdata[index].append(float(loaddata[index][index2 + 1]))
            else:
                loadfloatdata[index].append(float(0))

    inputdata = [[[] for col in xrange(4)] for col in xrange(len(dates))]

    # 天气因素的的值被改变后用此变量接受
    inputdataChange = [[[] for col in xrange(4)] for col in xrange(len(dates))]
    outputdata = [[] for col in xrange(len(dates))]
    for i in xrange(len(dates)):
        inputdata[i][0] = temperature[i]
        inputdata[i][1] = rain[i]
        inputdata[i][2] = wind[i]
        inputdata[i][3] = loadfloatdata[i]
        outputdata[i] = loadfloatdata[i]
        # =======================================
        inputdataChange[i][0] = temperatureChange[i]
        inputdataChange[i][1] = rainChange[i]
        inputdataChange[i][2] = windChange[i]
        inputdataChange[i][3] = loadfloatdata[i]
    # 输入数据的下标对应的日期与输出数据的下表对应的日期是一致的
    diffList = [[] for col in xrange(len(dates))]
    for i in xrange(len(dates)):
        # diff=bpNetwork(inputdataChange[i],[outputdata[i]])[0]- bpNetwork(inputdata[i],[outputdata[i]])[0]
        diff = bpNetwork(inputdataChange[i], [outputdata[i]])[0] - outputdata[i]
        diffList[i] = sum(diff) / len(diff)
    avg = sum(diffList) / len(diffList)
    if isnan(avg):
        print "Error"
    else:
        print  '%.3f' % avg
except BaseException as e:
    s=sys.exc_info()
    logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
except psycopg2.DatabaseError as e:
    s=sys.exc_info()
    logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
finally:
    conn.close()
