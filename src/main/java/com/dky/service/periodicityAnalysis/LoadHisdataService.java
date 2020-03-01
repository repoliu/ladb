package com.dky.service.periodicityAnalysis;

import com.dky.entity.*;

import java.util.List;

/**
 * 创建人-夏利勇 ，   时间 2017-10-26 14:09
 */
public interface LoadHisdataService {


    void insert(LoadWeektypeload288 record);
    void insert(LoadWeektypeload96 record);

    int insert(LoadHisdata record);

    LoadHisdata selectByAreaAndMdate(LoadHisdataKey loadHisdataKey);
    List<LoadHisdata> selectQuery96(LoadHisdataKey key);//XLY
    List<LoadHisdata> selectQuery288(LoadHisdataKey key);//XLY
    String selectOne(LoadHisdataKey key);//XLY
    String selectTwo(LoadHisdataKey key);//XLY
    List<LoadHisdata> selectAll(LoadHisdataKey key);//XLY

    List<LoadHisdata> find288MdateListByKey(LoadHisdataKey loadHisdataKey);

    List<LoadHisdata> find96MdateListByKey(LoadHisdataKey loadHisdataKey);

    List<LoadWeektypeload288> find288WeekListByKey(LoadWeektypeload288 loadWeektypeload288);

    List<LoadWeektypeload96> find96WeekListByKey(LoadWeektypeload96 loadWeektypeload96);

    //查询week96曲线表最大的结束日期
    String week96MaxEndDayServer(LoadWeektypeload96Key loadWeektypeload96);
    //查询week288曲线表最大的结束日期
    String week288MaxEndDayServer(LoadWeektypeload288Key loadWeektypeload288);
}
