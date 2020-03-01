package com.dky.service.generatrixAnalysis.impl;

import com.dky.entity.BusloadType;
import com.dky.entity.LdBusDvld1;
import com.dky.entity.vo.BusNameValue;
import com.dky.entity.vo.ChartsOption;
import com.dky.mapper.BusloadTypeMapper;
import com.dky.mapper.LdBusDvld1Mapper;
import com.dky.service.generatrixAnalysis.BusloadTypeService;
import com.dky.service.generatrixAnalysis.LdBusDvld1Service;
import com.dky.util.BusThread;
import com.dky.util.daoOperate.MultipleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("busloadTypeService")
public class BusloadTypeServiceImpl implements BusloadTypeService {

    @Autowired
    private BusloadTypeMapper busloadTypeMapper;


    @Autowired
    private LdBusDvld1Service ldBusDvld1Service;


    @Autowired
    private LdBusDvld1Mapper ldBusDvld1Mapper;

    private String datasource = "dataSource2";

    @Override
    public List<BusloadType> selectData(BusloadType busloadType) {
        return busloadTypeMapper.selectData(busloadType);
    }


    @Override
    public List<ChartsOption> selectData(String dccDwscr) {
        List<ChartsOption> chartsOptions = new LinkedList<>();//这个是返回的数据

        //总的叫      全部负荷
        //温度敏感型  空调负荷    降雨敏感型
        BusloadType busloadType = new BusloadType();
        busloadType.setDccDwscr(dccDwscr);

        String nameAll = "全部负荷";
        int numberAll = 0;
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        numberAll = ldBusDvld1Service.selectCount(dccDwscr);//这个是先求出这个地区有多少条

        if (numberAll > 0) {
            ChartsOption chartsOption = new ChartsOption();

            busloadType.setBusloadType("温度敏感型负荷");
            String[] string0 = {"#fd87ab", "#34abec"};
            //{'#36e4bb','#ea97dd','#7493e5','#a99cf4','#fd87ab','#34abec', '#ebc54a','#fff45c',       '#b3d465','#cadd9c'}
            chartsOptions.add(toChartsOption(numberAll, busloadTypeMapper.selectCount(busloadType), "温度敏感型负荷", nameAll, string0));

            busloadType.setBusloadType("空调负荷");
            String[] string1 = {"#66cdaa", "#34abec"};
            chartsOptions.add(toChartsOption(numberAll, busloadTypeMapper.selectCount(busloadType), "空调负荷", nameAll, string1));

            busloadType.setBusloadType("降雨敏感型负荷");
            String[] string2 = {"#b3d465", "#34abec"};
            chartsOptions.add(toChartsOption(numberAll, busloadTypeMapper.selectCount(busloadType), "降雨敏感型负荷", nameAll, string2));


            //String[] str = {};//{ "温度、降雨负荷", "降雨负荷", "温度负荷", "空调负荷","空调、降雨负荷"};

           // chartsOption.setHengZhou(str);

            List<BusNameValue> list = new LinkedList<>();
            BusNameValue valueOne = new BusNameValue();
            BusNameValue valueTwo = new BusNameValue();
            BusNameValue valueThree = new BusNameValue();
            BusNameValue valueFour = new BusNameValue();
            BusNameValue valueFive = new BusNameValue();

            int twoNumber = 0;
            int threeNumber = 0;
            int fourNumber = 0;
            int fiveNumber = 0;

            twoNumber =busloadTypeMapper.selectJiangYuSingle(dccDwscr, "降雨敏感型负荷", "温度敏感型负荷");
            fourNumber = busloadTypeMapper.selectIntersectionSingle(dccDwscr, "降雨敏感型负荷", "温度敏感型负荷");
            fiveNumber = busloadTypeMapper.selectIntersectionSingle(dccDwscr, "空调负荷", "降雨敏感型负荷");


            int  number1=busloadTypeMapper.selectCountAreaAllSingle(dccDwscr,"温度敏感型负荷");
            int  number2=busloadTypeMapper.selectCountAreaAllSingle(dccDwscr,"空调负荷");
            threeNumber =  number1- number2-fourNumber+fiveNumber;


            List<String> listString=new ArrayList<>();


            //给第三个赋值
            valueTwo.setName("降雨负荷");
            valueTwo.setValue(Math.abs(twoNumber) + "");
            list.add(valueTwo);
            listString.add("降雨负荷");

            //给第四个赋值
            valueThree.setName("温度负荷");
            valueThree.setValue(Math.abs(threeNumber) + "");
            list.add(valueThree);
            listString.add("温度负荷");



            //给第一个赋值
            if (fiveNumber != 0) {
                valueFive.setName("空调、降雨负荷");
                valueFive.setValue(Math.abs(fiveNumber) + "");
                list.add(valueFive);
                listString.add("空调、降雨负荷");
            }

            //给第二个赋值
            if (fourNumber != 0) {
                valueFour.setName("温度、降雨负荷");
                valueFour.setValue(Math.abs(fourNumber) + "");
                list.add(valueFour);
                listString.add("温度、降雨负荷");
            }

            //切换回原来的数据库
            MultipleDataSource.clearDBType();

            String[] str = new String[listString.size()];//{ "温度、降雨负荷", "降雨负荷", "温度负荷", "空调负荷","空调、降雨负荷"};


            for (int i=0;i<listString.size();i++){
                str[i]=listString.get(i);
            }
            chartsOption.setHengZhou(str);



            //这个是饼图的颜色
            String[] string3 = { "#b3d465", "#fd87ab","#601e74","red"};

            chartsOption.setNameValueList(list);
            chartsOption.setColor(string3);
            chartsOptions.add(chartsOption);
        }
        return chartsOptions;
    }


    /**
     * 这个方法是点击的不是最后一级地区树，就会有查询多个地区的数据
     *
     * @param dcc
     * @param dccDwscr
     * @return
     */
    @Override
    public List<ChartsOption> selectDataTwo(String dcc, String dccDwscr) {
        List<ChartsOption> chartsOptions = new LinkedList<>();//这个是返回的数据

        List<LdBusDvld1> listLdbus = new LinkedList<>();
        MultipleDataSource.setDataSourceKey(datasource);//注意这里在调用service前切换到datasource2的数据源
        if (dcc.equals("dcc2_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue2(dccDwscr);
        } else if (dcc.equals("dcc3_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue3(dccDwscr);
        } else if (dcc.equals("dcc4_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue4(dccDwscr);
        } else if (dcc.equals("dcc5_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue5(dccDwscr);
        }
        //总的叫      全部负荷
        //温度敏感型  空调负荷    降雨敏感型

        String nameAll = "全部负荷";
        int numberAll = 0;

        List<String> listArea = new ArrayList<>();

        for (int i = 0; i < listLdbus.size(); i++) {
            listArea.add(listLdbus.get(i).getDcc1Descr());
        }
        numberAll = ldBusDvld1Mapper.selectCountAreaAll(listArea);
        if (numberAll > 0) {
            ChartsOption chartsOption = new ChartsOption();
            String[] string0 = {"#fd87ab", "#34abec"};
            //{'#36e4bb','#ea97dd','#7493e5','#a99cf4','#fd87ab','#34abec', '#ebc54a','#fff45c',       '#b3d465','#cadd9c'}
            int wenDuNumber = 0;
            wenDuNumber = busloadTypeMapper.selectCountAreaAll(listArea, "温度敏感型负荷");
            chartsOptions.add(toChartsOption(numberAll, wenDuNumber, "温度敏感型负荷", nameAll, string0));

            String[] string1 = {"#66cdaa", "#34abec"};
            int kongTiaoNumber = 0;
            kongTiaoNumber = busloadTypeMapper.selectCountAreaAll(listArea, "空调负荷");
            chartsOptions.add(toChartsOption(numberAll, kongTiaoNumber, "空调负荷", nameAll, string1));

            String[] string2 = {"#b3d465", "#34abec"};
            int jiangYuNumber = 0;

            jiangYuNumber = busloadTypeMapper.selectCountAreaAll(listArea, "降雨敏感型负荷");
            chartsOptions.add(toChartsOption(numberAll, jiangYuNumber, "降雨敏感型负荷", nameAll, string2));


            BusloadType loadType = new BusloadType();
            loadType.setDccDwscr(dccDwscr);



            List<BusNameValue> list = new LinkedList<>();
            BusNameValue valueTwo = new BusNameValue();
            BusNameValue valueThree = new BusNameValue();
            BusNameValue valueFour = new BusNameValue();
            BusNameValue valueFive = new BusNameValue();

            int twoNumber = 0;
            int threeNumber = 0;
            int fourNumber = 0;
            int fiveNumber = 0;


            twoNumber = busloadTypeMapper.selectJiangYu(listArea, "降雨敏感型负荷", "温度敏感型负荷");
            fourNumber = busloadTypeMapper.selectIntersection(listArea, "降雨敏感型负荷", "温度敏感型负荷");
            fiveNumber = busloadTypeMapper.selectIntersection(listArea, "空调负荷", "降雨敏感型负荷");
            int  number1=busloadTypeMapper.selectCountAreaAll(listArea,"温度敏感型负荷");
            int  number2=busloadTypeMapper.selectCountAreaAll(listArea,"空调负荷");
            threeNumber =  number1- number2-fourNumber+fiveNumber;


            MultipleDataSource.clearDBType();

            //给第三个赋值
            valueTwo.setName("降雨负荷");
            valueTwo.setValue(Math.abs(twoNumber) + "");
            list.add(valueTwo);

            //给第四个赋值
            valueThree.setName("温度负荷");
            valueThree.setValue( Math.abs(threeNumber)  + "");


            list.add(valueThree);


            //给第一个赋值
            if (fiveNumber != 0) {
                valueFive.setName("空调、降雨负荷");
                valueFive.setValue(Math.abs(fiveNumber) + "");
                list.add(valueFive);
            }

            //给第二个赋值
            if (fourNumber != 0) {
                valueFour.setName("温度、降雨负荷");
                valueFour.setValue(Math.abs(fourNumber) + "");
                list.add(valueFour);
            }

            String[] string3 = { "#b3d465", "#fd87ab",    "#601e74","red"};
            String[] str = {"降雨负荷", "温度负荷",  "温度、降雨负荷","空调、降雨负荷"};

            chartsOption.setHengZhou(str);
            chartsOption.setNameValueList(list);
            chartsOption.setColor(string3);

            chartsOptions.add(chartsOption);


        }
        return chartsOptions;
    }

    /**
     * @param numberAll//这个地区的总条数
     * @param numberValue//这个类型的条数
     * @param type                 //什么类型
     * @param typeName//全不负荷
     * @return
     */
    public ChartsOption toChartsOption(int numberAll, int numberValue, String type, String typeName, String[] color) {
        ChartsOption chartsOption = new ChartsOption();
        String[] str = {type, typeName};

        chartsOption.setHengZhou(str);

        List<BusNameValue> list = new LinkedList<>();
        BusNameValue valueOne = new BusNameValue();
        BusNameValue valueTwo = new BusNameValue();
        //给第一个赋值
        valueOne.setName(type);
        valueOne.setValue(numberValue + "");
        //给第二个赋值
        valueTwo.setName(typeName);
        valueTwo.setValue((numberAll - numberValue) + "");

        list.add(valueOne);
        list.add(valueTwo);

        chartsOption.setNameValueList(list);
        chartsOption.setColor(color);

        return chartsOption;
    }


    @Override
    public List<BusloadType> selectAllArea(String busloadType, List<String> listString) {
        return busloadTypeMapper.selectAllArea(busloadType, listString);
    }

    @Override
    public void insert(BusloadType busloadType) {
        busloadTypeMapper.insert(busloadType);
    }


}
