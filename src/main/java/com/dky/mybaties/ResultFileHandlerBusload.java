package com.dky.mybaties;

import com.dky.util.DateUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 17:13 2018/3/5
 */
public class ResultFileHandlerBusload implements ResultHandler {

    @Override
    public void handleResult(ResultContext resultContext) {
        Map map = (Map) resultContext.getResultObject();
        String date = (String) map.get("mdate");
        File f = null;
        Date a = null;
        try {
            a = DateUtils.parse(date,DateUtils.YMDN_P);
            f = new File("D:\\busload201809\\"+date+".txt");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        String date_ = DateUtils.format(a,DateUtils.YMDH_P);
        long id = (long) map.get("id");
        int index = 0;
        for(int i = 0; i < 24 ; i++) {
            String is;
            if(i<10) {
                is = "0"+i+":";
            } else {
                is = i+":";
            }
            for (int j = 0;j < 60 ; j+=5) {
                String js;
                if(j < 10) {
                    js = "0"+j+":00";
                } else {
                    js = j +":00";
                }
                String ins;
                if (index < 10) {
                    ins="f0"+index;
                } else {
                    ins = "f"+index;
                }
                sb.append(date_).append(" ").append(is).append(js).append('\001').append("0021990100").append('\001')
                        .append(id).append('\001').append(map.get(ins)).append('\001').append("有功").append('\001')
                        .append("").append('\n');
                index++;
            }
        }
        System.out.println(sb.toString());
        FileOutputStream out= null;
        try {
            out = new FileOutputStream(f, true);
            out.write(sb.toString().getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
