package com.dky.controller.generatrixAnalysis;

import com.dky.entity.*;
import com.dky.service.generatrixAnalysis.MhSgDevPwrtransfmH1MeaService;
import com.dky.util.daoOperate.MultipleDataSource;
import com.google.common.collect.Maps;
import com.dky.controller.generatrixAnalysis.base.nodes.Nodes;
import com.dky.controller.generatrixAnalysis.base.nodes.State;
import com.dky.service.generatrixAnalysis.BusHisdataService;
import com.dky.service.generatrixAnalysis.DbAreaService;
import com.dky.service.generatrixAnalysis.LdBusDvldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sks on 2017/3/28.
 */
@Controller
@RequestMapping("/HisData")
public class HisDataController {

    @Autowired
    BusHisdataService busHisdataService;

    @Autowired
    DbAreaService dbAreaService;

    @Autowired
    LdBusDvldService ldBusDvldService;

    @Autowired
    MhSgDevPwrtransfmH1MeaService sgDevPwrtransfmH1MeaService;

    private String datasource = "dataSource2";
    Logger logger = Logger.getLogger(HisDataController.class.getName());

    @RequestMapping("/maxDate")
    @ResponseBody
    public MhSgDevPwrtransfmH1Mea maxDate(){
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        // 获取根据年份变化，从而获取相对应的表的year
        String year = new SimpleDateFormat("yyyy").format(new Date());
        // 由于生产和开发之间的数据库差异，当前默认为tmpTableName2，在生产环境时，请切换到tmpTableName
        String tmpTableName = "mh_sg_dev_pwrtransfm_h1_mea_" + year;
        String tmpTableName2 = "mh_sg_dev_pwrtransfm_h1_mea_2015";
        MhSgDevPwrtransfmH1Mea maxDate = null;
        try {
              maxDate = sgDevPwrtransfmH1MeaService.queryMaxDate(tmpTableName);
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        MultipleDataSource.clearDBType();
        return maxDate;
    }


    @RequestMapping("/show")
    public @ResponseBody
    List getHisdata(String area, String date) {
        Set<String> areaList = new HashSet<String>();//某省份下属电厂集合
        Map<String, Map> datamap = Maps.newConcurrentMap();
        List<Nodes> nodesList = new ArrayList<Nodes>();
        List<LdBusDvld1> ldBusDvldBeanList = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        try {
            if (area.contains("全网")) {
                areaList = ldBusDvldService.powerStationListAll(area);
                ldBusDvldBeanList = ldBusDvldService.selectLdBusDvldAll(area);
            }else {
                areaList = ldBusDvldService.powerStationList(area);
                ldBusDvldBeanList = ldBusDvldService.selectLdBusDvld(area);
            }
            long end = System.currentTimeMillis();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        MultipleDataSource.clearDBType();
        Map<String, Set<String>> stLdMap = new HashMap<String, Set<String>>();// 厂站负荷映射
        Map<String, String> stIdMap = new HashMap<String, String>();// 厂站名和ID映射
        Map<String, String> ldIdMap = new HashMap<String, String>();// 负荷和ID映射

        if (ldBusDvldBeanList != null && ldBusDvldBeanList.size() > 0) {
            for (int i = 0; i < ldBusDvldBeanList.size(); i++) {
                LdBusDvld1 ldbusData = ldBusDvldBeanList.get(i);
                String st = ldbusData.getStDescr();// 厂站
                String ld = ldbusData.getLdDescr();// 变压器
                stIdMap.put(st, ldbusData.getStId());
                ldIdMap.put(ld, ldbusData.getLdId());

                if (stLdMap.containsKey(st)) {
                    stLdMap.get(st).add(ld);
                } else {
                    Set<String> ldSet = new HashSet<String>();
                    ldSet.add(ld);
                    stLdMap.put(st, ldSet);// 厂站StDescr() 与LdDescr() 负荷StId映射
                }
            }
        }
        int x = 0;
        // 遍历集合拿到厂站进行插入set集合，防止重复
        Set<String> ldBusSet = new HashSet<>();
        for (int i = 0; i < ldBusDvldBeanList.size(); i++) {
            ldBusSet.add(ldBusDvldBeanList.get(i).getStDescr());
        }
        for (String ldBusSetStr : ldBusSet) {
            Nodes node_foss = new Nodes();
            node_foss.setText(ldBusSetStr);
            node_foss.setSelectable(false);
            State state = new State();
            state.setExpanded(true);//此处设置树是否展开
            node_foss.setState(state);

            Set<String> ldSet = stLdMap.get(ldBusSetStr);// 负荷集合
            if (ldSet != null) {
                for (Iterator<String> ldIterator = ldSet.iterator(); ldIterator.hasNext(); ) {
                    String ld = ldIterator.next();
                    x++;
                    Nodes node_plt = new Nodes();
                    String[] str = ld.split("/");
                    node_plt.setText(str[1]);
                    node_plt.setId(ldIdMap.get(ld));
                    node_plt.setState(state);//此处设置树是否展开
                    node_foss.getNodes().add(node_plt);

                }
            }
            nodesList.add(node_foss);
        }
        List<Nodes> areanodesList = new ArrayList<Nodes>();
        for (int i = 0; i < nodesList.size(); i++) {
            for (String areas : areaList) { //此处传入的是一个地址集合，通过点击地区筛选得到
                if (nodesList.get(i).getText().equals(areas)) {
                    areanodesList.add(nodesList.get(i));
                }
            }
        }
        return areanodesList;
    }

    //此方法是为母线地区搜索框提供
    @RequestMapping("/fuzzyQuery")
    @ResponseBody
    public List fuzzyQuery(String area, String keyword, String date) {

        Set<String> areaList = new HashSet<String>();//某省份下属电厂集合
        Map<String, Map> datamap = Maps.newConcurrentMap();
        List<Nodes> nodesList = new ArrayList<Nodes>();
        List<Dbarea> dbArealist = new ArrayList<>();
        List<LdBusDvld1> ldBusDvldBeanList = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        try {
            areaList = dbAreaService.powerStationList(area);
            dbArealist = dbAreaService.selectDbArea();
            ldBusDvldBeanList = ldBusDvldService.selectLdBusDvld(area);
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        MultipleDataSource.clearDBType();
        Map<String, Set<String>> dvStMap = new HashMap<String, Set<String>>();// 地区厂站映射
        Map<String, Set<String>> stLdMap = new HashMap<String, Set<String>>();// 厂站负荷映射
        Map<String, Long> stIdMap = new HashMap<String, Long>();// 厂站名和ID映射
        Map<String, Long> ldIdMap = new HashMap<String, Long>();// 负荷和ID映射
//        List<BusHisdataBean> buslist;
//        String datetime = date.substring(0,6);
//        String tmpTableName = "bus_hisdata"+datetime;

        if (ldBusDvldBeanList != null && ldBusDvldBeanList.size() > 0) {
            for (int i = 0; i < ldBusDvldBeanList.size(); i++) {
                LdBusDvld1 ldbusData = ldBusDvldBeanList.get(i);
                String st = ldbusData.getStDescr();// 厂站
                String ld = ldbusData.getLdDescr();// 变压器
                String dd1 = ldbusData.getDcc1Descr();// 地区
                stIdMap.put(st, Long.parseLong(ldbusData.getStId()));
                ldIdMap.put(ld, Long.parseLong(ldbusData.getLdId()));
                if (dvStMap.containsKey(dd1)) {
                    dvStMap.get(dd1).add(st);
                } else {
                    Set<String> stSet = new HashSet<String>();
                    stSet.add(st);//添加厂站
                    dvStMap.put(dd1, stSet);//地区DV_DESCR()与厂站关联ST_DESCR
                }
                if (stLdMap.containsKey(st + dd1)) {
                    stLdMap.get(st + dd1).add(ld);
                } else {
                    Set<String> ldSet = new HashSet<String>();
                    ldSet.add(ld);
                    stLdMap.put(st + dd1, ldSet);// 厂站StDescr()+Dcc1Descr() 与LdDescr() 负荷StId映射
                }
            }
        }

        for (int i = 0; i < dbArealist.size(); i++) {
            Dbarea ldbusData = dbArealist.get(i);
            String areaNode = ldbusData.getDname();// 地区节点
            Nodes node_foss = new Nodes();

            node_foss.setText(areaNode);

            Set<String> stSet = dvStMap.get(areaNode);// 厂站集合
            if (stSet != null) {
                boolean second = false;
                for (Iterator<String> stIterator = stSet.iterator(); stIterator.hasNext(); ) {
                    String st = stIterator.next();
                    //String laststr=st+"_"+stIdMap.get(st);

                    Nodes node_plt = new Nodes();
                    node_plt.setText(st);
//                    node_plt.setId(stIdMap.get(st));
                    node_plt.setSelectable(false);

                    Set<String> ldSet = stLdMap.get(st + areaNode);// 负荷集合
                    List setlist = new ArrayList();
                    if (ldSet != null) {
                        for (Iterator<String> ldIterator = ldSet.iterator(); ldIterator.hasNext(); ) {
                            String ld = ldIterator.next();
                            Nodes node_un = new Nodes();
                            String[] str = ld.split("/");
                            node_un.setText(str[1]);
//                            node_un.setId(ldIdMap.get(ld));
                            node_plt.getNodes().add(node_un);
                        }
                    }
                    if (!"".equals(keyword) && st.contains(keyword)) {//判断市级以下地点，控制菜单展开
                        State state = new State();
                        state.setExpanded(true);//此处设置树是否展开
                        state.setSelected(true);
                        second = true;
                        node_plt.setState(state);
                    }
                    node_foss.getNodes().add(node_plt);
                }
                boolean first = false;
                State state2 = null;

                if (!"".equals(keyword) && areaNode.contains(keyword)) {//判断市级地点，控制菜单展开，选中
                    state2 = new State();
                    node_foss.setSelectable(false);
                    first = true;
                    //二级节点布尔值与一级节点布尔值做或运算，只要有一个符合即打开
                    state2.setExpanded(true);//此处设置树是否展开
                    state2.setSelected(true);
                } else {
                    state2 = new State();
                    state2.setExpanded((second || first));//此处设置树是否展开
                    state2.setSelected((second || first));

                }
                node_foss.setState(state2);
            }

            nodesList.add(node_foss);

        }
        List<Nodes> areanodesList = new ArrayList<Nodes>();
        for (int i = 0; i < nodesList.size(); i++) {
            for (String areas : areaList) { //此处传入的是一个地址集合，通过点击省份筛选得到
                if (nodesList.get(i).getText().equals(areas)) {
                    areanodesList.add(nodesList.get(i));
                }
            }
        }

        return areanodesList;

    }


    @RequestMapping("/search")
    public @ResponseBody
    Map<String, Object> getSelectHisdata(String id, String time, String checkbox_fixedb, String checkbox_state) {

        Map<String, Object> map = Maps.newHashMap();
        // 截取时间，给下面进行拼接
        String[] timelist = time.split("-");
        String mdate = time;
//        for (int i = 0; i < timelist.length; i++) {
//            mdate += timelist[i];
//        }
        String year = new SimpleDateFormat("yyyy").format(new Date());
        String tmpTableName = null;
        if (!"".equals(timelist[0]) && timelist[0] != null) {
            tmpTableName = "mh_sg_dev_pwrtransfm_h1_mea_" + timelist[0];
        } else {
            tmpTableName = "mh_sg_dev_pwrtransfm_h1_mea_"+year;
        }
        List<MhSgDevPwrtransfmH1Mea> MhSgDevPwrtransfmH1MeaDatalist = new ArrayList<>();
        MultipleDataSource.setDataSourceKey(datasource);
        try {
            MhSgDevPwrtransfmH1MeaDatalist = sgDevPwrtransfmH1MeaService.selectBusParamHisdata(tmpTableName, id, mdate);
        } catch (Exception e) {
            if (time.isEmpty()) {
                logger.error(e.getMessage());
                MultipleDataSource.clearDBType();
            } else {
                MultipleDataSource.clearDBType();
                e.printStackTrace();
            }
        }
        MultipleDataSource.clearDBType();
//      用集合进行添加数据,并存入到map中,最终页面进行接收
        List<String> measValueList = new ArrayList<>();
        List<String> createDatetimeList = new ArrayList<>();
        for (int i = 0; i < MhSgDevPwrtransfmH1MeaDatalist.size(); i++) {
            BigDecimal measValue = MhSgDevPwrtransfmH1MeaDatalist.get(i).getMeasValue();
            String createDatetime = MhSgDevPwrtransfmH1MeaDatalist.get(i).getCreateDatetime();
            measValueList.add(String.valueOf(measValue));
            createDatetimeList.add(createDatetime);
        }
        map.put("measValue", measValueList);
        map.put("createDatetime", createDatetimeList);
//    此处是进行条件筛选
//        for (int i = 0; i < busHisdatalist.size(); i++) {
//            String source = busHisdatalist.get(i).getSource();
//            String falg = busHisdatalist.get(i).getFlag();
//
//            if (source.equals("ss")) {
//                if (falg.equals("w")) {
//                    map.put("whisdata", busHisdatalist.get(i));
//                } else if (falg.equals("r")) {
//                    map.put("rhisdata", busHisdatalist.get(i));
//                }
//            } else if (source.equals("sr")) {
//                if (falg.equals("w")) {
//                    map.put("zwhisdata", busHisdatalist.get(i));
//                } else if (falg.equals("r")) {
//                    map.put("zrhisdata", busHisdatalist.get(i));
//                }
//            }
//
//        }

        return map;
    }

}