# -*- coding: utf-8 -*-
import sys
import threading
import time
import minTempAveloadCLuster
import typeCLuster
import rainDayLoadCf
import matirx2_RainyDay

# area="长沙"
# mdate="2018"
area=sys.argv[1].decode('GBK').encode('utf-8')#地区值，传入中文，程序会去dbarea表对应查找序号
mdate=sys.argv[2][0:4]#传入日期，截取字符串年
class myThread (threading.Thread):   #继承父类threading.Thread
    def __init__(self, area, mdate):
        threading.Thread.__init__(self)
        self.area = area
        self.mdate = mdate

    def run(self):                   #把要执行的代码写到run函数里面 线程在创建后会直接运行run函数
        cluster1=minTempAveloadCLuster.cluster()
        cluster1.start(self.area,self.mdate)
        typeCLuster.start(self.area,self.mdate)


class myThread3 (threading.Thread):   #继承父类threading.Thread
    def __init__(self,area, mdate):
        threading.Thread.__init__(self)
        self.area = area
        self.mdate = mdate

    def run(self):                   #把要执行的代码写到run函数里面 线程在创建后会直接运行run函数
        #此处具有先后关系，应该先往load_hisdata_96_cluster中插入数据，再从中提取数据
        #进行相关性分析
        matirx2_RainyDay.start(self.area,self.mdate)
        rainDayLoadCf.start(self.area,self.mdate)





start=time.time()
threadLock = threading.Lock()
threads = []

# 创建新线程
thread1 = myThread(area, mdate)
thread3 = myThread3(area, mdate)

# 开启新线程
thread1.start()
thread3.start()

# 添加线程到线程列表
threads.append(thread1)
threads.append(thread3)
# 等待所有线程完成
for t in threads:
    t.join()

end=time.time()
print True
