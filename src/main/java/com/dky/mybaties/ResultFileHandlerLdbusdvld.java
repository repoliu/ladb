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
public class ResultFileHandlerLdbusdvld implements ResultHandler {

    @Override
    public void handleResult(ResultContext resultContext) {
        Map map = (Map) resultContext.getResultObject();
        File f = null;
        Date a = new Date();
        String date = DateUtils.format(a,DateUtils.YMDN_P);
        try {
            f = new File("D:\\ldbusdvld\\"+date+".txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String date_ = DateUtils.format(a,DateUtils.YMDHMSH_P);
        StringBuilder sb = new StringBuilder();
        sb.append(map.get("bid")).append('\001').append(map.get("bname")).append('\001').append(map.get("aid")).append('\001').append(map.get("aname")).append('\001')
                .append(map.get("a1")).append('\001').append(map.get("b1")).append('\001').append(map.get("a2")).append('\001').append(map.get("b2")).append('\001')
                .append(map.get("a3")).append('\001').append(map.get("b3")).append('\001').append(map.get("a4")).append('\001').append(map.get("b4")).append('\001')
                .append(map.get("a5")).append('\001').append(map.get("b5")).append('\001').append("0").append('\001').append("0").append('\001')
                .append(220).append('\001').append(date_).append('\n');

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
