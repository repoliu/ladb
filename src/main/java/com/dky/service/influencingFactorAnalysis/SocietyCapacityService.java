package com.dky.service.influencingFactorAnalysis;


import com.dky.entity.SocietyCapacity;
import com.dky.entity.vo.ChartsOption;

import java.util.List;
import java.util.Map;

public interface SocietyCapacityService {

    int insert(SocietyCapacity societyCapacity);

    List<ChartsOption> selectQuery(String date, String area, String num, String nianXian,String anNiu);

    Map selectMaxDate(String string, String id) ;//这个是加载的时候获取最大时间的

}
