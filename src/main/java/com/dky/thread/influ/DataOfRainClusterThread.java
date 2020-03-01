package com.dky.thread.influ;

import com.dky.entity.vo.Point96;
import com.dky.mapper.LoadHisdata96ClusterMapper;
import com.dky.util.ChartsUtils;
import com.dky.util.ObjToArraysUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 14:38 2018/3/5
 */
public class DataOfRainClusterThread implements Callable<String[]> {
    private Integer area;
    private String mdate;
    private LoadHisdata96ClusterMapper loadHisdata96ClusterMapper;
    private Integer loadvalueType;
    public DataOfRainClusterThread() {
    }

    public DataOfRainClusterThread(Integer area, String mdate,Integer loadvalueType, LoadHisdata96ClusterMapper loadHisdata96ClusterMapper) {
        this.area = area;
        this.mdate = mdate;
        this.loadvalueType = loadvalueType;
        this.loadHisdata96ClusterMapper = loadHisdata96ClusterMapper;
    }

    @Override
    public String[] call() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("area",area);
        params.put("mdate",mdate);
        params.put("loadvalueType",loadvalueType);
        params.put("clusterType",3);
        Point96 p1 = loadHisdata96ClusterMapper.findListExceptClusterTypeByParams(params);
        if (p1 == null){
            return null;
        }
        return ObjToArraysUtils.getFieldValues(p1);
    }
}
