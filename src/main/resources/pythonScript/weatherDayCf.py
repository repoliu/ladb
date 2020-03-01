# -*- coding: utf-8 -*-
import time;  # 引入time模块

from numpy import *
import corrcoef as crf
import sys
from util.readProperties import *
import psycopg2
from util.log import *
reload(sys)
sys.setdefaultencoding('utf-8')


#此脚本的作用时新的一天产生新的数据，而又不想计算全年时可以只计算新产生的一天的，同时更新当月的相关性
if __name__ == '__main__':
    # area="福州"
    # mdate="20150607"
    area=sys.argv[1].decode('GBK').encode('utf-8') #地区值，传入中文，程序会去dbarea表对应查找序号
    mdate=sys.argv[2]#传入日

    month=mdate[0:6]
    dict=readDb1Properties()
    currentSchema=dict['currentSchema']
    conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
    cur = conn.cursor()
    conn.set_session(autocommit=True)
    try:
         #删除当天的相关系数
        sqlDayDelete="select "+currentSchema+".p_delete('"+dict['currentSchema']+"','relation_support','relation_type " \
                      "in(''温度'',''人体舒适度'',''湿度'') and areaname=''"+area+"'' " \
                      "and mdate=''"+mdate+"''')"
        #删除当前所属月的相关系数
        sqlMonthDelete="select "+currentSchema+".p_delete('"+dict['currentSchema']+"','relation_support','areaname=''"+area+"'' and " \
                        "relation_type in(''温度'',''人体舒适度'',''湿度'') and mdate=''"+month+"''')"


        cur.execute(sqlDayDelete)
        
        cur.execute(sqlMonthDelete)
        

        #===================负荷值筛选==============================
        #查找当天天气数据
        sql1="select distinct to_number(to_char(time,'hh24'),'99G999D9S')*60,wp,temperature,humidity,rain " \
             "from "+currentSchema+".autovalue where to_char(time,'yyyymmdd') ='"+mdate+"' and " \
             "stcd=(select stcd from "+currentSchema+".qstation where stnm = '"+area+"' ) " \
             # "order by to_char(time,'yyyy-mm-dd hh24:mi:ss')"
        start = time.time()
        cur.execute(sql1)
        weatherColData=cur.fetchall()

        weatherCompareInfo=[0 for col in xrange(len(weatherColData))]
        for index in xrange(len(weatherColData)):
            weatherCompareInfo[index]=list(weatherColData[index])
            weatherCompareInfo[index].append(crf.computeCosiness(weatherColData[index][1],weatherColData[index][2],weatherColData[index][3]))
        weatherRowData=map(list,zip(*weatherCompareInfo))#0时间 1风速 2温度 3湿度 4降雨 5舒适度

        timePoint=weatherRowData[0]#存储负荷要查询的点
        for index in xrange(len(timePoint)):
            if timePoint[index]==0:
                timePoint[index]="f00"
            else:
                timePoint[index]="f"+str(int(timePoint[index]))
        timePointTemp=','.join(timePoint)

        #当天负荷数据
        sql2="select distinct area,mdate,"+timePointTemp+" " \
            "from "+currentSchema+".load_hisdata where area=(select area from "+currentSchema+".dbarea where dname='"+area+"') " \
            "and mdate='"+mdate+"' order by mdate"
        cur.execute(sql2)
        loaddata=cur.fetchall()
        loadCompareData=list(loaddata[0])
        insertSql="insert into "+currentSchema+".relation_support(area,id,areaname,mdate,relation_type,relation_degree,ver_time) values "
        nowtime=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
        if(len(loadCompareData)>0 and len(weatherRowData)>0):
          insertSql+="('"+str(loadCompareData[0])+"','"+str(loadCompareData[0])+"','" \
                         +area+"','"+str(mdate)+"','"+ \
                         "温度"+"','"+str(crf.corrcoef(loadCompareData[2:],weatherRowData[2]))+"','"+ \
                         str(nowtime)+"'),"

          insertSql+="('"+str(loadCompareData[0])+"','"+str(loadCompareData[0])+"','" \
                         +area+"','"+str(mdate)+"','"+ \
                         "湿度"+"','"+str(crf.corrcoef(loadCompareData[2:],weatherRowData[3]))+"','"+ \
                         str(nowtime)+"'),"

          # insertSql+="('"+str(loadCompareData[0])+"','"+str(loadCompareData[0])+"','" \
          #                +area+"','"+str(mdate)+"','"+ \
          #                "降雨量"+"','"+str(crf.corrcoef(loadCompareData[2:],weatherRowData[4]))+"','"+ \
          #                str(nowtime)+"'),"

          insertSql+="('"+str(loadCompareData[0])+"','"+str(loadCompareData[0])+"','" \
                         +area+"','"+str(mdate)+"','"+ \
                         "人体舒适度"+"','"+str(crf.corrcoef(loadCompareData[2:],weatherRowData[5]))+"','"+ \
                         str(nowtime)+"'),"
          #产生当天的相关性分析
          cur.execute(insertSql[:-1])

        #重新计算当月的相关性
        sql9="select substring(mdate,0,7) as mdate,AREA,ID,AREANAME,relation_type,avg(RELATION_DEGREE) " \
             "from "+currentSchema+".relation_support where relation_type in('人体舒适度','温度','湿度') " \
             "and areaname='"+area+"' and substring(mdate,0,7)='"+month+"' group by substring(mdate,0,7)," \
             "relation_type,AREA,ID,AREANAME order by mdate"
        cur.execute(sql9)
        monthAvg=cur.fetchall()
        insertSql2="insert into "+currentSchema+".relation_support(mdate,area,id,areaname,relation_type,relation_degree,ver_time) values"
        for data in  monthAvg:
            insertSql2+="('"+str(data[0])+"','"+str(data[1])+"','"+str(data[2])+"','"+str(data[3])+"','" \
                        +str(data[4])+"','"+str(data[5])+"','"+str(nowtime)+"'),"
        if(len(monthAvg)>0):
          cur.execute(insertSql2[:-1])
          print True
        end = time.time()
        
    except BaseException as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    except psycopg2.DatabaseError as e:
        s=sys.exc_info()
        logger(os.path.realpath(__file__)).error("Error '%s' happened on line %d" % (s[1],s[2].tb_lineno))
    finally:
        
        conn.close()