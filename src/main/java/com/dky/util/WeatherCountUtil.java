package com.dky.util;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:31 2018/1/10
 */
public class WeatherCountUtil {

    public static int computeCosiness(int wd,int temperature , int humidity){
        double ws = WeatherCountUtil.sumWsByWp(wd);
        int cosi=(int)((1.818*temperature+ 18.18)*(0.88 + 0.002*humidity/100)+(temperature- 32) / (45 -temperature)- 3.2*ws+ 18.2);
        return WeatherCountUtil.sumCosiness(cosi);
    }

    /**
     * 通过舒适度指数的值，返回舒适度等级
     * @param cosi 舒适度指数
     * @return 舒适度等级
     */
    public static int sumCosiness(int cosi) {
        if ( cosi < 25){
            return  1;
        } else if (cosi < 39){
            return 2;
        } else if (cosi < 51){
            return 3;
        } else if (cosi < 59){
            return 4;
        } else if (cosi < 71){
            return 5;
        } else  if (cosi < 76){
            return 6;
        } else if (cosi < 80){
            return 7;
        } else if (cosi < 86){
            return 8;
        } else {
            return 9;
        }
    }

    /**
     * 通过风力等级，来确定平均风速
     * @param wp 风力等级
     * @return 风速
     */
    public static double sumWsByWp(int wp) {
        double result = 0;
        switch (wp){
            case 0:
                result = 0.1;
                break;
            case 1:
                result = 0.9;
                break;
            case 2:
                result = 2.45;
                break;
            case 3:
                result = 4.4;
                break;
            case 4:
                result = 6.7;
                break;
            case 5:
                result = 9.35;
                break;
            case 6:
                result = 12.3;
                break;
            case 7:
                result = 15.5;
                break;
            case 8:
                result = 18.95;
                break;
            case 9:
                result = 22.6;
                break;
            case 10:
                result = 26.45;
                break;
            case 11:
                result = 30.55;
                break;
            case 12:
                result = 34.8;
                break;
            case 13:
                result = 39.2;
                break;
            case 14:
                result = 43.8;
                break;
        }
        return result;
    }
}
