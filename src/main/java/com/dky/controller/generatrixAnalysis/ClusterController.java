package com.dky.controller.generatrixAnalysis;



import com.dky.entity.BusHisdataBean;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Series;
import com.dky.service.generatrixAnalysis.BusHisdataService;
import com.dky.util.StringUtil;
import com.dky.util.TimePoint;
import com.dky.util.daoOperate.MultipleDataSource;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuhaijian
 * @date Create in 2017/11/16
 */
@Controller
@RequestMapping("/ClusterController")
public class ClusterController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    BusHisdataService busHisdataService;
    private String datasource = "dataSource2";
    private String path=ClusterController.class.getResource("/").getPath().substring(1);

    @RequestMapping("/getSelectHisdata")
    @ResponseBody
    public Map<String, Object> getSelectHisdata(String mdate, String province) throws Exception{
        List<BusHisdataBean> busHisdatalist=new ArrayList<>();
        Map<String, Object> map = Maps.newHashMap();
        String date="";
        try {
        MultipleDataSource.setDataSourceKey(datasource);
        checkClusterData(province,sdf.format(sdf2.parse(mdate)));

        String tmpTableName ="";
        String year="2014";
        //date=sdf.format(sdf2.parse(mdate));
        if(!StringUtil.isNullOrEmpty(mdate)) {
            year = mdate.substring(0,4);
        }
            //此处动态判断表名
            tmpTableName="bus_hisdata"+year+"_cluster";


            busHisdatalist=  busHisdataService.selectBusHisdataByDate(tmpTableName,sdf.format(sdf2.parse(mdate)),province);

            if(busHisdatalist.size()>0) {
                date=sdf2.format(sdf.parse(busHisdatalist.get(0).getMdate()));
            }
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }


        MultipleDataSource.clearDBType();

        //四种聚类分类，所以循环四次，从list集合中分别拿出四种数据并放到map中
        for(int type=1;type<=4;type++) {
            List<ChartsOption> chartsOptionList=new LinkedList<ChartsOption>();
            for (BusHisdataBean busHisdataBean : busHisdatalist) {
                if(type==busHisdataBean.getClusterType())
                {
                ChartsOption chartsOption = new ChartsOption();
                chartsOption.setHengZhou(TimePoint.timeCreate(288));
                chartsOption.setLegend(busHisdataBean.getMdate()+"-"+busHisdataBean.getDescr());
                Series series = new Series();
                series.setName(busHisdataBean.getMdate()+"-"+busHisdataBean.getDescr());
                series.setSmooth(true);
                series.setType("line");
                series.setShowAllSymbol(false);//在母线中不需要现实
                Vector<Double> vectorf288 = busHisdataBean.getVectorf288();
                String array[] = new String[vectorf288.size()];
                for (int i = 0; i < vectorf288.size(); i++) {
                    array[i] = String.valueOf(vectorf288.get(i));
                }
                series.setData(array);
                chartsOption.setSeries(series);
                chartsOptionList.add(chartsOption);

            }
            }
            switch (type) {
                case 1:
                    map.put("cluster1", chartsOptionList);
                    break;
                case 2:
                    map.put("cluster2", chartsOptionList);
                    break;
                case 3:
                    map.put("cluster3", chartsOptionList);
                    break;
                case 4:
                    map.put("cluster4", chartsOptionList);
                    break;
            }
        }
        //值时间回掉给输入框
        map.put("date",mdate);
        return map;
    }

    //对比的是不同省份间相同时期数据条数是否相同
    public boolean checkClusterData(String province,String mdate){
        boolean b=false;//false为不相同
        String tmpTableName="bus_hisdata"+mdate.substring(0,4);
        if(!busHisdataService.compareCount(tmpTableName, mdate, province)){
           //传入的参数第一个为province，第二个为mdate
            try {
                province = new String(province.toString().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String[] args11 = new String[] { "python", path+"pythonScript/generatrixCluster.py",province, mdate};
            pythonExcute(args11);
            b=true;
        }
        //此处是点击触发时候的逻辑
        return b;
    }

    public static void pythonExcute(String path[]){
        try{
            System.out.println("start");
            Process pr = Runtime.getRuntime().exec(path);

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            // process.waitFor()返回值为0表示我们调用python脚本成功，
            // 返回值为1表示调用python脚本失败
            int re=pr.waitFor();
            System.out.println("end");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //获取昨天的日期
    public String yesterDayDate() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        System.out.println(new SimpleDateFormat("yyyyMMdd").format(time));
        return new SimpleDateFormat("yyyyMMdd").format(time);

    }
}
