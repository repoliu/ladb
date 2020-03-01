package com.dky.util;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author liuhaijian
 * @date Create in 2018/1/9
 */
public class PythonOperation {
     static Logger logger= Logger.getLogger(PythonOperation.class.getName());
    //调用python时要使用此方法，传入的参数类似
    // String[] args11 = new String[] { "python", path+"pythonScript/typeCluster.py","福州", "20150809" };
    //下标不可改动，下标2为要调用的脚本，此顺序不能乱，超过下标2的均为传入的参数
    public static boolean pythonExcute(String path[]){
        try{
            System.out.println("start");
            String os = System.getProperty("os.name");
            if(!os.toLowerCase().startsWith("win")){
                String address="/"+path[1];
                path[1]=address;
            }
            Process pr = Runtime.getRuntime().exec(path);

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            String result = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result = line;
            }
            in.close();
            // process.waitFor()返回值为0表示我们调用python脚本成功，
            // 返回值为1表示调用python脚本失败
            int re=pr.waitFor();
            logger.debug("path>>>>>"+path[1]);
            logger.debug("process.waitFor()>>>>>"+re);
            System.out.println("end");
            if ("True".equals(result)){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
                e.printStackTrace();
                return false;
        }
    }

    //返回字符串
    public static String pythonExcute2(String path[]){
        try{
            System.out.println("start"); 
            String os = System.getProperty("os.name");

            if(!os.toLowerCase().startsWith("win")){
                String address="/"+path[1];
                path[1]=address;
            }
            Process pr = Runtime.getRuntime().exec(path);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            String result = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result = line;
            }
            in.close();
            // process.waitFor()返回值为0表示我们调用python脚本成功，
            // 返回值为1表示调用python脚本失败
            int re=pr.waitFor();
            logger.debug("path>>>>>"+path[1]);
            logger.debug("process.waitFor()>>>>>"+re);
            System.out.println("end");
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }

    }

}
