# -*- coding: UTF-8 -*-

import psycopg2

from util.readProperties import *
import sys
sys.setdefaultencoding('utf8')
def selectOperate():
    cursor.execute("select id,name,password,singal from load_9.zztest where id>2")
    rows=cursor.fetchall()
    for row in rows:
        print 'id=',row[0], ',name=',row[1],',pwd=',row[2],',singal=',row[3],'\n'



if __name__=='__main__':
    dict=readDb2Properties()
    dict['currentSchema']
    conn = psycopg2.connect(database=dict['database'],user=dict['user'],password=dict['passwd'],host=dict['host'],port=dict['port'])
    cursor=conn.cursor()
    conn.set_session(autocommit=True)
    try:
        sql="select count(*) from loadfor.dbarea"
        cursor.execute(sql)
        rowdata=cursor.fetchall()
        print rowdata
    except psycopg2.DatabaseError as e:
        print e
    finally:
        conn.commit()
        conn.close()