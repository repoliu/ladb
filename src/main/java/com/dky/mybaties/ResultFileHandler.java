package com.dky.mybaties;

import com.dky.util.DateUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 17:13 2018/3/5
 */
public class ResultFileHandler implements ResultHandler {

    /**
     * 对每条记录进行处理
     * @param resultContext 每条记录的结果
     */
    @Override
    public void handleResult(ResultContext resultContext) {
        //将结果转换为map类型，key值为xml文件中resultmap中的property（字段名），value是对应字段的值
        Map map = (Map) resultContext.getResultObject();
        //将time字段取出
        String date = (String) map.get("time");
        //创建File，文件
        File f = null;
        //时间类型的变量
        Date a = null;
        try {
            //将字符串类型转换为事件类型
            a = DateUtils.parse(date,DateUtils.YMDHMSH_P);
            //通过路径对f赋值，通过时间来命名，例：F:\\autovalue\\20150101.txt，如果还有地区，再把地区放进去
            f = new File("F:\\autovalue\\"+DateUtils.format(a,DateUtils.YMDN_P)+".txt");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //字符串缓冲变量
        StringBuilder sb = new StringBuilder();
        sb.append(map.get("stcd")).append('\001').append(DateUtils.format(a,DateUtils.YMDHMSH_P))
                .append('\001').append(map.get("temperature")).append('\001').append(map.get("rain"))
                .append('\001').append(map.get("enpresure")).append('\001').append(map.get("wp"))
                .append('\001').append(map.get("wd")).append('\001').append(map.get("humidity"))
                .append('\001').append(map.get("vtype")).append('\n');
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
