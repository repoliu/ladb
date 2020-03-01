package com.dky.service.generatrixAnalysis;

import com.dky.entity.BusHisdataBean;

import java.util.List;
import java.util.Set;

/**
 * Created by sks on 2017/3/28.
 */
public interface BusHisdataService {

    List<BusHisdataBean> selectBusHisdata(String tmpTableName, String id, String mdate);

    List<BusHisdataBean> selectBusParamHisdata(String tmpTableName, String id, String mdate, String checkbox_fixedb, String checkbox_state);

    //聚类分析的服务接口
    List<BusHisdataBean> selectBusHisdataByDate(String tmpTableName,String mdate,String province);

    boolean compareCount(String tmpTableName,String mdate,String province);

    Set<String> provinceList(String mdate,String tmpTableName);

}
