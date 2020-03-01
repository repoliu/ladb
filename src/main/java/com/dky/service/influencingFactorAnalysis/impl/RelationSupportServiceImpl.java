package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.RelationSupport;
import com.dky.entity.vo.ChartsOption;
import com.dky.entity.vo.Indicator;
import com.dky.mapper.RelationSupportMapper;
import com.dky.service.influencingFactorAnalysis.RelationSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("relationSupportService")
public class RelationSupportServiceImpl implements RelationSupportService {

    @Autowired
    private RelationSupportMapper relationSupportMapper;

    @Override
    public ChartsOption selectQuery(String area, String date, String nianXian) {
        RelationSupport relationSupport = new RelationSupport();
        List<RelationSupport> list = new LinkedList<>();
        //这是定义返回的到页面的数据，所有东西都放在这里边
        ChartsOption option = new ChartsOption();
        //第一次进入这个页面跳到这个方法的时候只传进来一个年份，比如2017，第二次进来传的是个年的区间，比如2015-2017这样的格式
        if (date.length() > 6) {
            String[] dateTime = new String[2];
            dateTime = date.split("-");
            relationSupport.setAreaname(area);
            relationSupport.setMdate(dateTime[1]);
            relationSupport.setMinDate((Integer.parseInt(dateTime[1]) - (Integer.parseInt(dateTime[1]) - Integer.parseInt(dateTime[0]))) + "");
        } else {
            date = date.substring(0, 4);
            relationSupport.setAreaname(area);
            relationSupport.setMdate(date);
            relationSupport.setMinDate((Integer.parseInt(date) - Integer.parseInt(nianXian)+1) + "");
        }

        list = relationSupportMapper.selectQuery(relationSupport);
        //筛选一下看看都有那一年的
        List<String> stringList = new ArrayList<>();
        //这个循环代表了把查出来的数据按年份区分，看看都些年份
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                stringList.add(list.get(i).getMdate());
            } else if (i > 0) {
                int number = 0;
                for (int k = 0; k < stringList.size(); k++) {
                    if (stringList.get(k).equals(list.get(i).getMdate())) {
                        number++;
                    }
                }
                if (number == 0) {
                    stringList.add(list.get(i).getMdate());
                }
            }
        }
        //这个是图例方便看都有哪一年的信息
        String [] tuLi=new String[stringList.size()];
        for (int i=0;i<stringList.size();i++){
            tuLi[i]=stringList.get(i);
        }
        List<Indicator> data = new LinkedList<Indicator>();
        //这个循环代表了用年来区分数据
        for (int j = 0; j < stringList.size(); j++) {
            List<Indicator> indicatorList = new LinkedList<>();
            int number = 0;
            int count = 0;
            //这个循环确定一下有几个不同的年份
            for (int i = 0; i < list.size(); i++) {
                if (stringList.get(j).equals(list.get(i).getMdate())) {
                    number++;
                }
            }
            String[] str = new String[number];
            Indicator ind = new Indicator();
            //把相同年份的数据放的一个集合中
            for (int i = 0; i < list.size(); i++) {
                if (stringList.get(j).equals(list.get(i).getMdate())) {
                    Indicator indicator = new Indicator();
                    indicator.setName(list.get(i).getRelationType());
                    indicatorList.add(indicator);
                    str[count] = list.get(i).getRelationDegree().toString();
                    count++;
                }
                if (i==(list.size()-1)&&j==0){
                    option.setIndicatorList(indicatorList);
                }else if (i==(list.size()-1)&&j>0){
                    if (option.getIndicatorList().size()<indicatorList.size()){
                        option.getIndicatorList().clear();
                        option.setIndicatorList(indicatorList);
                    }
                }
            }
            ind.setName(stringList.get(j));
            ind.setValue(str);
            data.add(ind);
        }
        //这一步是看那一年的数据最多，在这个雷达图上显示的时候这个data集合里下标为0的这个实体类中的value的长度必须是最长的，因为在雷达图上他按第一个的长度显示，
        // 如果data集合里下标为0的这个实体类中的value长度2，剩下的实体类中value的长度不论是多长都会只显示2个
        int number=0;
        //找出集合中第几个实体的value的长度最长
        for (int i=0;i<data.size();i++){
            if (i>0){
                if (data.get(i).getValue().length>(data.get(i-1).getValue().length)){
                    number=i;
                }
            }
        }
        if (number>0){
            //把实体中value最长的实体拿出来，然后删除掉
            Indicator indicatorValue=new Indicator();
            indicatorValue=data.get(number);
            //删掉实体中value最长的实体
            data.remove(number);
            //把实体中value最长的实体data集合第0个位置
            data.add(0,indicatorValue);
        }
        //获得雷达图的数据是否大于0
        if (option!=null&&option.getIndicatorList()!=null&&option.getIndicatorList().size()>0){
            //雷达图中元素不满足3个的时候给它加俩空的形成三角平面，否则会是一条直线
            if (option.getIndicatorList().size()==2){
                Indicator ind=new Indicator();
                String [] str=new String[0];
                ind.setName("");
                ind.setMax(0);
                ind.setValue(str);
                data.add(ind);
                option.getIndicatorList().add(ind);
            }else if (option.getIndicatorList().size()==1){
                Indicator ind=new Indicator();
                String [] str=new String[0];
                ind.setName("");
                ind.setMax(0);
                ind.setValue(str);
                data.add(ind);

                Indicator indicator=new Indicator();
                indicator.setName("");
                indicator.setMax(0);
                indicator.setValue(str);
                data.add(indicator);

                option.getIndicatorList().add(ind);
                option.getIndicatorList().add(indicator);
            }
        }

        option.setDataInfluence(data);
        option.setHengZhou(tuLi);
        return option;
    }


    @Override
    public int insert(RelationSupport relationSupport) {
        return relationSupportMapper.insert(relationSupport);
    }
}
