# -*- coding: UTF-8 -*-

import numpy as np
#import matplotlib.pyplot as plt


def logsig(x):
    return 1/(1+np.exp(-x))
#inputdata,outputdata一定是封装好的列表数据
def bpNetwork(inputdata,outputdata):
    samplein = np.mat(inputdata) #3*20
    sampleinminmax = np.array([samplein.min(axis=1).T.tolist()[0],samplein.max(axis=1).T.tolist()[0]]).transpose()#3*2，对应最大值最小值
    sampleout = np.mat(outputdata)#2*20
    sampleoutminmax = np.array([sampleout.min(axis=1).T.tolist()[0],sampleout.max(axis=1).T.tolist()[0]]).transpose()#2*2，对应最大值最小值
    #3*20
    sampleinnorm = (2*(np.array(samplein.T)-sampleinminmax.transpose()[0])/(sampleinminmax.transpose()[1]-sampleinminmax.transpose()[0])-1).transpose()
    #2*20
    sampleoutnorm = (2*(np.array(sampleout.T).astype(float)-sampleoutminmax.transpose()[0])/(sampleoutminmax.transpose()[1]-sampleoutminmax.transpose()[0])-1).transpose()


    #给输出样本添加噪音
    noise = 0.03*np.random.rand(sampleoutnorm.shape[0],sampleoutnorm.shape[1])
    sampleoutnorm += noise


    maxepochs = 10000 #最大迭代次数
    learnrate = 0.035 #学习率
    errorfinal = 0.65*10**(-3) #最后错误率
    samnum = np.shape(inputdata)[1]#输入节点矩阵中列数
    indim =len(inputdata) #输入节点
    outdim = len(outputdata) #输出节点
    hiddenunitnum =10 #隐藏节点

    #w 权重  b阈值
    w1 = 0.5*np.random.rand(hiddenunitnum,indim)-0.1
    b1 = 0.5*np.random.rand(hiddenunitnum,1)-0.1
    w2 = 0.5*np.random.rand(outdim,hiddenunitnum)-0.1
    b2 = 0.5*np.random.rand(outdim,1)-0.1

    errhistory = []

    for i in range(maxepochs):
        hiddenout = logsig((np.dot(w1,sampleinnorm).transpose()+b1.transpose())).transpose()
        networkout = (np.dot(w2,hiddenout).transpose()+b2.transpose()).transpose()
        err = sampleoutnorm - networkout
        sse = sum(sum(err**2))
        errhistory.append(sse)
        if sse < errorfinal:
            break

        delta2 = err

        delta1 = np.dot(w2.transpose(),delta2)*hiddenout*(1-hiddenout)

        dw2 = np.dot(delta2,hiddenout.transpose())
        db2 = np.dot(delta2,np.ones((samnum,1)))

        dw1 = np.dot(delta1,sampleinnorm.transpose())
        db1 = np.dot(delta1,np.ones((samnum,1)))

        w2 += learnrate*dw2
        b2 += learnrate*db2

        w1 += learnrate*dw1
        b1 += learnrate*db1




    # 误差曲线图
    errhistory10 = np.log10(errhistory)
    minerr = min(errhistory10)

    # plt.plot(errhistory10)
    # #range用法，start开始，end结束，step步长
    # plt.plot(range(0,i+1000,1000),[minerr]*len(range(0,i+1000,1000)))
    #
    # ax=plt.gca()
    # ax.set_yticks([-2,-1,0,1,2,minerr])
    # ax.set_yticklabels([u'$10^{-2}$',u'$10^{-1}$',u'$1$',u'$10^{1}$',u'$10^{2}$',str(('%.4f'%np.power(10,minerr)))])
    # ax.set_xlabel('iteration')
    # ax.set_ylabel('error')
    # ax.set_title('Error History')
    # ax.set_title('Error History')
    # plt.savefig('errorhistory.png',dpi=700)
    # plt.close()



    # 仿真输出和实际输出对比图
    hiddenout = logsig((np.dot(w1,sampleinnorm).transpose()+b1.transpose())).transpose()
    networkout = (np.dot(w2,hiddenout).transpose()+b2.transpose()).transpose()
    diff = sampleoutminmax[:,1]-sampleoutminmax[:,0]
    networkout2 = (networkout+1)/2
    networkout2[0] = networkout2[0]*diff[0]+sampleoutminmax[0][0]


    # sampleout = np.array(sampleout)
    #
    # fig,axes = plt.subplots(nrows=1,ncols=1,figsize=(12,10))
    # line1, =axes.plot(networkout2[0],'k',marker = u'$\circ$')
    # line2, = axes.plot(sampleout[0],'r',markeredgecolor='b',marker = u'$\star$',markersize=9)
    #
    # axes.legend((line1,line2),('simulation output','real output'),loc = 'upper left')
    #
    # axes.set_ylabel('loaddata')
    #
    # xticks = range(0,samnum,2)
    # axes.set_xticks(xticks)
    # axes.set_xlabel(u'time')
    # axes.set_title('loaddata-forecast')
    #
    #
    #
    # fig.savefig('simulation.png',dpi=500,bbox_inches='tight')
    # plt.close()
    # plt.show()
    return  networkout2

