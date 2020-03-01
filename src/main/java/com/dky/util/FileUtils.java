package com.dky.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: YangSL
 * @Description: 文件操作工具类
 * @Date: Create in 10:27 2018/2/23
 */
public class FileUtils
{
    /**
     * 将传入的流文件保存在服务器上
     * @param stream 传入的流文件
     * @param path 保存的地址
     * @throws IOException 文件流错误
     */
    @SuppressWarnings("unused")
    public static void saveFileByStream(InputStream stream, String path) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        File saveFile = new File(path);
        boolean mkdirs = saveFile.getParentFile().mkdirs();
        boolean createNewFile = saveFile.createNewFile();
        OutputStream out = new FileOutputStream(saveFile);
        while ((len = stream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        stream.close();
        out.close();
    }

    /**
     * 生成保存文件的地址
     * @param dir 保存文件的目录名
     * @param areaname 文件所属地区名
     * @param fileName 文件名
     * @param suffix 保存格式
     * @return 返回地址的字符串
     */
    public static String creatPath(String dir,String areaname,String fileName,String suffix){
        SimpleDateFormat fmtYMD = new SimpleDateFormat("/yyyy年/MM月/dd日/");
        SimpleDateFormat fmdH = new SimpleDateFormat("/HH点mm分ss秒_");
        Date today = new Date();
        return "../../上传目录/大数据负荷分析/"+ dir + fmtYMD.format(today)+ areaname
                +  fmdH.format(today) + fileName + suffix;
    }
}
