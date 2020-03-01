package com.dky.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author liuhaijian
 * @date Create in 2017/12/14
 */
public class ReadProperties {
    private static final String encode = "UTF-8";//文件的编码格式
    public static String readProperties(String item) {
        ReadProperties loadProp=new ReadProperties();
        InputStream in = loadProp.getClass().getResourceAsStream("/defaultarea.properties");
        Properties prop = new Properties();
        String address=null;
        InputStreamReader inputStreamReader=null;
        try {
            inputStreamReader=new InputStreamReader(in, "utf-8");
            prop.load(inputStreamReader);
            address = prop.getProperty(item);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStreamReader.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return address;
    }

    //指定要读取的文件及属性
    public static String readProperties(String fileName,String item) {
        ReadProperties loadProp=new ReadProperties();
        InputStream in = loadProp.getClass().getResourceAsStream(fileName);
        Properties prop = new Properties();
        String info=null;
        InputStreamReader inputStreamReader=null;
        try {
            inputStreamReader=new InputStreamReader(in, "utf-8");
            prop.load(inputStreamReader);
            info = prop.getProperty(item);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStreamReader.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return info;
    }
}
