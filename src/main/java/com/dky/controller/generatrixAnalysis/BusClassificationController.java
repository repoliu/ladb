package com.dky.controller.generatrixAnalysis;

import com.dky.entity.BusloadType;
import com.dky.entity.LdBusDvld1;
import com.dky.entity.forecast.DygraphsTable;
import com.dky.entity.vo.BusReturnValue;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.TreeDataTemp;
import com.dky.service.generatrixAnalysis.BusloadTypeService;
import com.dky.service.generatrixAnalysis.BusloadTypeSupportService;
import com.dky.service.generatrixAnalysis.LdBusDvld1Service;
import com.dky.util.daoOperate.MultipleDataSource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/busClassificationController")
public class BusClassificationController {


    @Autowired
    private LdBusDvld1Service ldBusDvld1Service;


    @Autowired
    private BusloadTypeService busloadTypeService;

    @Autowired
    private BusloadTypeSupportService busloadTypeSupportService;

    List<TreeDataTemp> tempLists = new ArrayList<>();


    private String datasource = "dataSource2";


    /**
     * 这个方法用来跳转页面的
     *
     * @param areaname//代表了哪个地区
     * @param paramsName//代表了是哪种类型
     * @param request
     * @return
     */
    @RequestMapping("/busJump")
    public String busJump(@Param("areaname") String areaname, @Param("paramsName") String paramsName, HttpServletRequest request) {
        request.setAttribute("areaname", areaname);
        request.setAttribute("paramsName", paramsName);
        return "/generatrixAnalysis/busJump";
    }

    /**
     * 这个是用来跳转页面的
     *
     * @return
     */
    @RequestMapping("/show")
    public String show(HttpServletRequest request) {
        String busName = request.getParameter("busName");
        if (busName != null && busName != "") {
            request.setAttribute("busName", busName);
        } else {
            busName = "四川全网";
            request.setAttribute("busName", "四川全网");
        }

        Map<String, String> mapArea = new HashMap<>();
        mapArea = strintoNumber(busName);

        List<String> listString = new ArrayList<>();
        listString = numberList(mapArea.get("number"), mapArea.get("dcc"), busName);
        String dateX = null;
        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            dateX = busloadTypeSupportService.selectMaxDate(listString);
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        if (dateX==null){
            SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy");
            dateX=format.format(new Date());
        }

        // int number = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
        int number = Integer.parseInt((dateX.substring(0, 4)));
        String[] busloadTypeSupportDate = {number - 3 + "", number - 2 + "", number - 1 + "", number + "", number + 1 + "", number + 2 + "", number + 3 + ""};
        request.setAttribute("busloadTypeSupportDate", busloadTypeSupportDate);
        request.setAttribute("defaultDate", number);
        return "generatrixAnalysis/busClassification";
    }


    /**
     * 这个是用来跳转页面的
     */
    @RequestMapping("/tobusJump")
    public String tobusJump(HttpServletRequest request) {
        String busName = request.getParameter("busName");
        if (busName != null && busName != "") {
            request.setAttribute("busName", busName);
        } else {
            request.setAttribute("busName", "四川全网");
        }
        return "/generatrixAnalysis/busJump";
    }


    /**
     * 这个方法时查ldbusdvld表dcc1_descr这个字段是否存在这个地区名称
     * @param areaname
     * @return
     */
    @RequestMapping("/treeTrueFalse")
    @ResponseBody
    public Boolean treeTrueFalse(@Param("areaname") String areaname) {
        int number = 0;
        Boolean trueFalse = false;
        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            number = ldBusDvld1Service.selectCount(areaname);
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        if (number > 0) {
            trueFalse = true;
        }
        return trueFalse;
    }


    /**
     * 这个方法是查询饼图的方法，查询的是最后一级
     *
     * @param areaName//这个代表了地区
     * @return
     */
    @RequestMapping("/selectData")
    @ResponseBody
    public List<ChartsOption> selectData(@Param("areaName") String areaName) {
        return busloadTypeService.selectData(areaName);
    }

    /**
     * 这个方法是查询饼图的方法，查询的不是最后一级
     *
     * @param areaName//这个代表了地区
     * @return
     */
    @RequestMapping("/selectDataTwo")
    @ResponseBody
    public List<ChartsOption> selectDataTwo(@Param("areaName") String areaName) {//
        List<ChartsOption> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map = strintoNumber(areaName);
        if (Integer.parseInt(map.get("number")) > 0) {
            list = busloadTypeService.selectDataTwo(map.get("dcc"), areaName);
        }
        return list;
    }

    /**
     * 这个是查询柱状图
     *
     * @param areaName//代表地区
     * @param date//时间
     * @return
     */
    @RequestMapping("/selectBusloadTypeSupport")
    @ResponseBody
    public List<ChartsOption> selectBusloadTypeSupport(@Param("areaName") String areaName, @Param("date") String date) {//
        List<ChartsOption> list = new ArrayList<>();
     /*   if (date.equals(null)||date.equals("")){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy");//参数：指定格式的类型
            date=sdf.format(new Date());
        }*/
        int number = Integer.parseInt((date.substring(0, 4)));
        //这个代表7个时间按钮的时间
        String[] busloadTypeSupportDate = {number - 3 + "", number - 2 + "", number - 1 + "", number + "", number + 1 + "", number + 2 + "", number + 3 + ""};
        ChartsOption option = new ChartsOption();
        int numberAreaName=0;

        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            numberAreaName=ldBusDvld1Service.selectCountDcc1(areaName);
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }

        if (numberAreaName>0) {
            option = busloadTypeSupportService.selectAreaDate(date, "温度敏感型负荷", areaName);
            option.setData(busloadTypeSupportDate);
            list.add(option);

            option = busloadTypeSupportService.selectAreaDate(date, "降雨敏感型负荷", areaName);
            list.add(option);

            option = busloadTypeSupportService.selectAreaDate(date, "空调负荷", areaName);
            list.add(option);
        } else {
            Map<String, String> mapArea = new HashMap<>();
            mapArea = strintoNumber(areaName);

            List<String> listString = new ArrayList<>();
            listString = numberList(mapArea.get("number"), mapArea.get("dcc"), areaName);

            option = busloadTypeSupportService.selectListAreaDate(date, "温度敏感型负荷", listString);
            option.setData(busloadTypeSupportDate);
            list.add(option);

            option = busloadTypeSupportService.selectListAreaDate(date, "降雨敏感型负荷", listString);
            list.add(option);

            option = busloadTypeSupportService.selectListAreaDate(date, "空调负荷", listString);
            list.add(option);

        }
        return list;
    }


    /**
     * 这个方法是得到某父级个地区下边有哪些子集地区
     *
     * @param number
     * @param dcc
     * @param areaName
     * @return
     */
    public List<String> numberList(String number, String dcc, String areaName) {
        List<String> listString = new ArrayList<>();
        if (Integer.parseInt(number) > 0) {
            List<LdBusDvld1> listLdbus = new LinkedList<>();
            try {
                MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
                listLdbus = ldBusDvld1Service.selectTreeTwo(dcc, areaName);
                MultipleDataSource.clearDBType();
            } catch (Exception e) {
                MultipleDataSource.clearDBType();
                e.printStackTrace();
            }
            for (int i = 0; i < listLdbus.size(); i++) {
                listString.add(listLdbus.get(i).getDcc1Descr());
            }
        }
        return listString;
    }

    @RequestMapping("/selectDcc5")
    @ResponseBody
    public Map selectDcc5(@Param("areaName") String areaName) {//
        Map<String, String> map = new HashMap<>();
        if (areaName != null && areaName != "") {
            map = strintoNumber(areaName);
            return map;
        }
        return map;
    }


    public Map strintoNumber(String areaName) {
        Map<String, String> map = new HashMap<>();
        String str = "dcc2_descr";
        map.put("number", "-1");
        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            if (ldBusDvld1Service.selectCountDcc(str, areaName) > 0) {
                map.put("number", "1");
                map.put("dcc", "dcc2_descr");
            } else {
                str = "dcc3_descr";
                if (ldBusDvld1Service.selectCountDcc3(areaName) > 0) {
                    map.put("number", "1");
                    map.put("dcc", "dcc3_descr");
                } else {
                    str = "dcc4_descr";
                    if (ldBusDvld1Service.selectCountDcc4(areaName) > 0) {
                        map.put("number", "1");
                        map.put("dcc", "dcc4_descr");
                    } else {
                        str = "dcc5_descr";
                        if (ldBusDvld1Service.selectCountDcc5(areaName) > 0) {
                            map.put("number", "1");
                            map.put("dcc", "dcc5_descr");
                        }
                    }
                }
            }
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("/selectData2")
    @ResponseBody
    public List<ChartsOption> selectData2(@Param("areaName") String areaName) {
        return busloadTypeService.selectData(areaName);
    }


    /**
     * 这个方法是查询母线饼图跳转到分类数据查询页面的查询数据的
     *
     * @param areaname//地区
     * @param paramsName//需要查询的类型
     */
    @RequestMapping("/busJumpData")
    @ResponseBody
    public Map busJumpData(@Param("areaname") String areaname, @Param("paramsName") String paramsName) {
        Map<String, Object> map = new HashMap<>();
        BusloadType busloadType = new BusloadType();
        busloadType.setDccDwscr(areaname);

        /**
         * 空调负荷
         */
        List<BusloadType> list1 = new ArrayList<>();
        busloadType.setBusloadType("空调负荷");
        //查出这个地区-温度敏感型负荷-的数据
        List<BusloadType> list3 = null;
        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            list1 = busloadTypeService.selectData(busloadType);
            //这个代表了表格的第一行
            List<List<DygraphsTable>> dygraphs1 = new ArrayList<>();
            dygraphs1.add(BusJumpAdditional.toData("空调负荷"));
            //这个代表了其他行数据
            List<BusReturnValue> busReturnValue1 = new ArrayList<>();
            busReturnValue1 = BusJumpAdditional.conversionBusReturnValue(list1);
            map.put("dygraphs1", dygraphs1);
            map.put("busReturnValue1", busReturnValue1);


            /**
             * 降雨敏感型负荷
             */
            List<BusloadType> list2 = new ArrayList<>();
            busloadType.setBusloadType("降雨敏感型负荷");
            //查出这个地区-温度敏感型负荷-的数据
            list2 = busloadTypeService.selectData(busloadType);
            //这个代表了表格的第一行
            List<List<DygraphsTable>> dygraphs2 = new ArrayList<>();
            dygraphs2.add(BusJumpAdditional.toData("降雨敏感型负荷"));
            //这个代表了其他行数据
            List<BusReturnValue> busReturnValue2 = new ArrayList<>();
            busReturnValue2 = BusJumpAdditional.conversionBusReturnValue(list2);
            map.put("dygraphs2", dygraphs2);
            map.put("busReturnValue2", busReturnValue2);


            /**
             * 温度敏感型负荷
             */
            list3 = new ArrayList<>();
            busloadType.setBusloadType("温度敏感型负荷");
            //查出这个地区-温度敏感型负荷-的数据
            list3 = busloadTypeService.selectData(busloadType);
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        //这个代表了表格的第一行
        List<List<DygraphsTable>> dygraphs3 = new ArrayList<>();
        dygraphs3.add(BusJumpAdditional.toData("温度敏感型负荷"));
        //这个代表了其他行数据
        List<BusReturnValue> busReturnValue3 = new ArrayList<>();
        busReturnValue3 = BusJumpAdditional.conversionBusReturnValue(list3);
        map.put("dygraphs3", dygraphs3);
        map.put("busReturnValue3", busReturnValue3);


        return map;

    }


    /**
     * 这个方法是查询母线饼图跳转到分类数据查询页面的查询数据的
     *
     * @param areaname//地区
     * @param paramsName//需要查询的类型
     */
    @RequestMapping("/busJumpDataTwo")
    @ResponseBody
    public Map busJumpDataTwo(@Param("areaname") String areaname, @Param("paramsName") String paramsName) {
        Map<String, String> mapArea = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        mapArea = strintoNumber(areaname);
        if (Integer.parseInt(mapArea.get("number")) > 0) {
            BusloadType busloadType = new BusloadType();
            List<LdBusDvld1> listLdbus = new LinkedList<>();
            List<BusReturnValue> busReturnValue1 = null;
            List<BusReturnValue> busReturnValue2 = null;
            List<BusReturnValue> busReturnValue3 = null;
            List<BusloadType> list1 = null;
            try {
                MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
                listLdbus = ldBusDvld1Service.selectTreeTwo(mapArea.get("dcc"), areaname);

                List<String> listString = new ArrayList<>();

                for (int i = 0; i < listLdbus.size(); i++) {
                    listString.add(listLdbus.get(i).getDcc1Descr());
                }


                //这个代表了表格的第一行
                List<List<DygraphsTable>> dygraphs1 = new ArrayList<>();
                dygraphs1.add(BusJumpAdditional.toData("空调负荷"));
                map.put("dygraphs1", dygraphs1);
                //这个代表了其他行数据
                busReturnValue1 = new ArrayList<>();


                //这个代表了表格的第一行
                List<List<DygraphsTable>> dygraphs2 = new ArrayList<>();
                dygraphs2.add(BusJumpAdditional.toData("降雨敏感型负荷"));
                map.put("dygraphs2", dygraphs2);
                //这个代表了其他行数据
                busReturnValue2 = new ArrayList<>();


                //这个代表了表格的第一行
                List<List<DygraphsTable>> dygraphs3 = new ArrayList<>();
                dygraphs3.add(BusJumpAdditional.toData("温度敏感型负荷"));
                map.put("dygraphs3", dygraphs3);
                //这个代表了其他行数据
                busReturnValue3 = new ArrayList<>();


                /**
                 * 空调负荷
                 */
                list1 = new ArrayList<>();
                //查出这个地区-温度敏感型负荷-的数据
                list1 = busloadTypeService.selectAllArea("空调负荷", listString);
                for (int k = 0; k < list1.size(); k++) {
                    BusloadType busloadKT = list1.get(k);
                    BusReturnValue busReturnValue = new BusReturnValue();
                    busReturnValue.setDevDescr(busloadKT.getDevDescr());
                    busReturnValue.setTypeDegree(busloadKT.getTypeDegree() + "");
                    busReturnValue1.add(busReturnValue);
                }

                /**
                 * 降雨敏感型负荷
                 */
                busloadType.setBusloadType("降雨敏感型负荷");
                //查出这个地区-温度敏感型负荷-的数据
                list1 = busloadTypeService.selectAllArea("降雨敏感型负荷", listString);
                for (int k = 0; k < list1.size(); k++) {
                    BusloadType busloadKT = list1.get(k);
                    BusReturnValue busReturnValue = new BusReturnValue();
                    busReturnValue.setDevDescr(busloadKT.getDevDescr());
                    busReturnValue.setTypeDegree(busloadKT.getTypeDegree() + "");
                    busReturnValue2.add(busReturnValue);
                }

                /**
                 * 温度敏感型负荷
                 */
                busloadType.setBusloadType("温度敏感型负荷");
                //查出这个地区-温度敏感型负荷-的数据
                list1 = busloadTypeService.selectAllArea("温度敏感型负荷", listString);
                MultipleDataSource.clearDBType();
                for (int k = 0; k < list1.size(); k++) {
                    BusloadType busloadKT = list1.get(k);
                    BusReturnValue busReturnValue = new BusReturnValue();
                    busReturnValue.setDevDescr(busloadKT.getDevDescr());
                    busReturnValue.setTypeDegree(busloadKT.getTypeDegree() + "");
                    busReturnValue3.add(busReturnValue);
                }
            } catch (Exception e) {
                MultipleDataSource.clearDBType();
                e.printStackTrace();
            }

            map.put("busReturnValue1", busReturnValue1);
            map.put("busReturnValue2", busReturnValue2);
            map.put("busReturnValue3", busReturnValue3);
        }

        return map;

    }


    /**
     * 这个是用来查询左侧树的，下边的方法也都是配合查询左侧树的
     *
     * @return
     */
    @RequestMapping("/tree")
    @ResponseBody
    public List<TreeDataTemp> tree() {
        tempLists = null;
        List<String[]> list = new ArrayList<>();
        List<LdBusDvld1> listLdb = new ArrayList<>();
        try {
            MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
            listLdb = ldBusDvld1Service.selectAll();
            MultipleDataSource.clearDBType();
        } catch (Exception e) {
            MultipleDataSource.clearDBType();
            e.printStackTrace();
        }
        for (int j = 0; j < listLdb.size(); j++) {
            String[] data = new String[5];
            data[0] = listLdb.get(j).getDcc5Descr();
            data[1] = listLdb.get(j).getDcc4Descr();
            data[2] = listLdb.get(j).getDcc3Descr();
            data[3] = listLdb.get(j).getDcc2Descr();
            data[4] = listLdb.get(j).getDcc1Descr();
            if (data[0].equals(null) || data[0].equals("")) {
                data[0] = data[1];
            }
            int number = 0;
            number = arrayIndex(data);
            String[] strData = new String[data.length - number];
            for (int i = 0; i < strData.length; i++) {
                strData[i] = data[number];
                number++;
            }
            treeNumber(strData);
        }
        return tempLists;
    }

    /**
     * 这个方法是找到重复的数据到哪不重复了
     *
     * @param array
     * @return
     */
    public int arrayIndex(String[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (!array[i - 1].equals(array[i])) {
                index = i - 1;
                return index;
            }
        }
        return index;
    }

    public int treeNumber(String[] data) {
        if (tempLists != null && tempLists.size() > 0) {
            int number = -1;
            //集合中是否有dcc5
            for (int i = 0; i < tempLists.size(); i++) {
                if (tempLists.get(i).getName().equals(data[0])) {
                    TreeDataTemp treeDataTemp5 = new TreeDataTemp();
                    treeDataTemp5 = tempLists.get(i);
                    int number5 = -1;
                    //是否存在dcc4
                    for (int j = 0; j < treeDataTemp5.getChildren().size(); j++) {
                        if (treeDataTemp5.getChildren().get(j).getName().equals(data[1])) {
                            if (data.length > 2) {
                                TreeDataTemp treeDataTemp4 = new TreeDataTemp();
                                treeDataTemp4 = treeDataTemp5.getChildren().get(j);
                                int number4 = -1;
                                //是否存在dcc3
                                for (int k = 0; k < treeDataTemp4.getChildren().size(); k++) {
                                    if (treeDataTemp4.getChildren().get(k).getName().equals(data[2])) {
                                        if (data.length > 3) {
                                            TreeDataTemp treeDataTemp3 = new TreeDataTemp();
                                            treeDataTemp3 = treeDataTemp4.getChildren().get(k);
                                            int number3 = -1;
                                            //dcc2是否存在
                                            for (int u = 0; u < treeDataTemp3.getChildren().size(); u++) {
                                                if (treeDataTemp3.getChildren().get(u).getName().equals(data[3])) {
                                                    if (data.length > 4) {
                                                        TreeDataTemp treeDataTemp2 = new TreeDataTemp();
                                                        treeDataTemp2 = treeDataTemp3.getChildren().get(u);
                                                        int number2 = -1;
                                                        for (int p = 0; p < treeDataTemp2.getChildren().size(); p++) {
                                                            if (treeDataTemp2.getName().equals(data[4])) {
                                                                number2++;
                                                                return 1;
                                                            }
                                                        }
                                                        if (number2 == -1) {
                                                            TreeDataTemp tree2 = new TreeDataTemp();
                                                            tree2.setName(data[4]);
                                                            tree2.setSpread(true);
                                                            tree2.setNumber(1);
                                                            tempLists.get(i).getChildren().get(j).getChildren().get(k).getChildren().get(u).getChildren().add(tree2);
                                                        }
                                                    } else {
                                                        return 1;
                                                    }
                                                    number3++;
                                                }
                                            }
                                            //没dcc2就加入
                                            if (number3 == -1) {
                                                if (data.length == 4) {
                                                    TreeDataTemp tree3 = new TreeDataTemp();
                                                    tree3.setName(data[3]);
                                                    tree3.setSpread(true);
                                                    tree3.setNumber(1);
                                                    tempLists.get(i).getChildren().get(j).getChildren().get(k).getChildren().add(tree3);
                                                    return 1;
                                                } else {
                                                    tempLists.get(i).getChildren().get(j).getChildren().get(k).getChildren().add(treeDataTemp(strings(data, 3)));
                                                    return 1;
                                                }
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                //没dcc3就加入
                                if (number4 == -1) {
                                    if (data.length == 3) {
                                        TreeDataTemp tree4 = new TreeDataTemp();
                                        tree4.setName(data[2]);
                                        tree4.setSpread(true);
                                        tree4.setNumber(1);
                                        tempLists.get(i).getChildren().get(j).getChildren().add(tree4);
                                        return 1;
                                    } else {
                                        tempLists.get(i).getChildren().get(j).getChildren().add(treeDataTemp(strings(data, 2)));
                                        return 1;
                                    }
                                }
                            } else {
                                return 1;
                            }
                        }
                    }
                    //没dcc4就加入
                    if (number5 == -1) {
                        if (data.length == 2) {
                            TreeDataTemp tree4 = new TreeDataTemp();
                            tree4.setName(data[1]);
                            tree4.setSpread(true);
                            tree4.setNumber(1);
                            tempLists.get(i).getChildren().add(tree4);
                            return 1;
                        } else {
                            tempLists.get(i).getChildren().add(treeDataTemp(strings(data, 1)));
                            return 1;
                        }
                    }
                    number++;
                }
            }
            //没dcc5就加入
            if (number == -1) {
                tempLists.add(treeDataTemp(data));
            }
        } else {//集合为空的时候 ,直接添加第一条
            List<TreeDataTemp> oneList = new ArrayList<>();
            tempLists = oneList;
            tempLists.add(treeDataTemp(data));
        }
        return 1;
    }

    /**
     * 这个是把这个数组的重复数据拍到，返回一个没有重复数据的数组
     *
     * @param data
     * @param number
     * @return
     */
    public String[] strings(String[] data, int number) {
        String[] str = new String[data.length - number];
        for (int i = number; i < data.length; i++) {
            str[i - number] = data[i];
        }
        return str;
    }


    /**
     * 这个方法是把每一条数据整理成一条小树
     *
     * @param data
     * @return
     */
    public TreeDataTemp treeDataTemp(String[] data) {
        TreeDataTemp treeDataTemp = new TreeDataTemp();
        TreeDataTemp oneTree = new TreeDataTemp();
        TreeDataTemp twoTree = new TreeDataTemp();
        TreeDataTemp threeTree = new TreeDataTemp();
        TreeDataTemp fourTree = new TreeDataTemp();
        TreeDataTemp fiveTree = new TreeDataTemp();
        List<TreeDataTemp> oneList = new ArrayList<>();
        List<TreeDataTemp> twoList = new ArrayList<>();
        List<TreeDataTemp> threeList = new ArrayList<>();
        List<TreeDataTemp> fourList = new ArrayList<>();
        List<TreeDataTemp> fiveList = new ArrayList<>();
        if (data.length == 1) {
            oneTree.setName(data[0]);
            oneTree.setSpread(true);
            oneTree.setNumber(1);
            treeDataTemp = oneTree;
        } else if (data.length == 2) {
            oneTree.setName(data[1]);
            oneTree.setSpread(true);
            oneTree.setNumber(1);
            twoTree.setName(data[0]);
            twoTree.setSpread(true);
            twoTree.setChildren(twoList);
            twoTree.getChildren().add(oneTree);
            treeDataTemp = twoTree;
        } else if (data.length == 3) {
            oneTree.setName(data[2]);
            oneTree.setSpread(true);
            oneTree.setNumber(1);
            twoTree.setName(data[1]);
            twoTree.setSpread(true);
            twoTree.setChildren(twoList);
            twoTree.getChildren().add(oneTree);
            threeTree.setName(data[0]);
            threeTree.setSpread(true);
            threeTree.setChildren(threeList);
            threeTree.getChildren().add(twoTree);
            treeDataTemp = threeTree;
        } else if (data.length == 4) {
            oneTree.setName(data[3]);
            oneTree.setSpread(true);
            oneTree.setNumber(1);
            twoTree.setName(data[2]);
            twoTree.setSpread(true);
            twoTree.setChildren(twoList);
            twoTree.getChildren().add(oneTree);
            threeTree.setName(data[1]);
            threeTree.setSpread(true);
            threeTree.setChildren(threeList);
            threeTree.getChildren().add(twoTree);
            fourTree.setName(data[0]);
            fourTree.setSpread(true);
            fourTree.setChildren(fourList);
            fourTree.getChildren().add(threeTree);
            treeDataTemp = fourTree;
        } else if (data.length == 5) {
            oneTree.setName(data[4]);
            oneTree.setSpread(true);
            oneTree.setNumber(1);
            twoTree.setName(data[3]);
            twoTree.setSpread(true);
            twoTree.setChildren(twoList);
            twoTree.getChildren().add(oneTree);
            threeTree.setName(data[2]);
            threeTree.setSpread(true);
            threeTree.setChildren(threeList);
            threeTree.getChildren().add(twoTree);
            fourTree.setName(data[1]);
            fourTree.setSpread(true);
            fourTree.setChildren(fourList);
            fourTree.getChildren().add(threeTree);
            fiveTree.setName(data[0]);
            fiveTree.setSpread(true);
            fiveTree.setChildren(fiveList);
            fiveTree.getChildren().add(fourTree);
            treeDataTemp = fiveTree;
        }
        return treeDataTemp;
    }


    @RequestMapping("/data")
    public String data() {
        return "generatrixAnalysis/busClassification";
    }


}
