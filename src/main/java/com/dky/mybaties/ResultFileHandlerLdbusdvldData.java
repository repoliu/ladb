package com.dky.mybaties;

import com.dky.util.DateUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 17:13 2018/3/5
 */
public class ResultFileHandlerLdbusdvldData implements ResultHandler {

    @Override
    public void handleResult(ResultContext resultContext) {
        Map map = (Map) resultContext.getResultObject();
        File f = null;
        Date a = new Date();
        String date = DateUtils.format(a,DateUtils.YMDN_P);
        try {
            f = new File("D:\\ldbusdvld.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String date_ = DateUtils.format(a,DateUtils.YMDHMSH_P);
        String abc = DateUtils.format((Date) map.get("ver_time"),DateUtils.YMDHMSH_P);
        StringBuilder sb = new StringBuilder();
        sb.append(map.get("ld_id")).append('\001').append(map.get("ld_descr")).append('\001').append(map.get("st_id")).append('\001').append(map.get("st_descr")).append('\001')
                .append(map.get("dv_id")).append('\001').append(map.get("dv_descr")).append('\001').append(map.get("co_id")).append('\001').append(map.get("co_descr")).append('\001')
                .append(map.get("co_id")).append('\001').append(map.get("co_descr")).append('\001').append(map.get("co_id")).append('\001').append(map.get("co_descr")).append('\001')
                .append(map.get("co_id")).append('\001').append(map.get("co_descr")).append('\001').append(map.get("flag")).append('\001').append(0).append('\001')
                .append(map.get("ld_vl")).append('\001').append(abc).append('\n');
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
