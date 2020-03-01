package com.dky.mapper;

import com.dky.entity.BusHisdataBean;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sks on 2017/3/28.
 */
public interface BusHisdataBeanMapper {

    List<BusHisdataBean> selectBusHisdata(HashMap datamap);

    List<BusHisdataBean> selectBusParamHisdata(HashMap datamap);

    List<BusHisdataBean> selectHisdataByTime(HashMap datamap);

    //查询表数据量
    int count(HashMap datamap);

    Set<String> province(HashMap datamap);

}
