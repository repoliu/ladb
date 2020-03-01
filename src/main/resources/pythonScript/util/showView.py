# -*- coding: UTF-8 -*-
import matplotlib.pyplot as plt


#人数(单位：万人)
population=[20.55,22.44,25.37,27.13,29.45,30.10,30.96,34.06,36.42,38.09,39.13,39.99,41.93,44.59,47.30,52.89,55.73,56.76,59.17,60.63]
#机动车数(单位：万辆)
vehicle=[0.6,0.75,0.85,0.9,1.05,1.35,1.45,1.6,1.7,1.85,2.15,2.2,2.25,2.35,2.5,2.6,2.7,2.85,2.95,3.1]
#公路面积(单位：万平方公里)
roadarea=[0.09,0.11,0.11,0.14,0.20,0.23,0.23,0.32,0.32,0.34,0.36,0.36,0.38,0.49,0.56,0.59,0.59,0.67,0.69,0.79]
#公路客运量(单位：万人)
passengertraffic=[5126,6217,7730,9145,10460,11387,12353,15750,18304,19836,21024,19490,20433,22598,25107,33442,36836,40548,42927,43462]
#公路货运量(单位：万吨)
freighttraffic=[1237,1379,1385,1399,1663,1714,1834,4322,8132,8936,11099,11203,10524,11115,13320,16762,18673,20724,20803,21804]


fig,axes = plt.subplots(nrows=2,ncols=1,figsize=(12,10))
line1, =axes[0].plot(population,'k',marker = u'$\circ$')
line2, = axes[0].plot(vehicle,'r',markeredgecolor='b',marker = u'$\star$',markersize=9)
line3, = axes[0].plot(roadarea,'r',markeredgecolor='b',marker = u'$\star$',markersize=9)

axes[0].legend((line1,line2,line3))





axes[0].set_xlabel(u'year')
axes[0].set_title('scaleplate')

line4, = axes[1].plot(passengertraffic,'k',marker = u'$\circ$')
line5, = axes[1].plot(freighttraffic,'r',markeredgecolor='b',marker = u'$\star$',markersize=9)
axes[1].legend((line4,line5),loc = 'upper left')





axes[1].set_xlabel(u'year')
axes[1].set_title('scaleplate')

plt.show()