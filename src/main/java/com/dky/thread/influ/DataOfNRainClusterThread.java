package com.dky.thread.influ;

import com.dky.entity.vo.Point96;
import com.dky.mapper.LoadHisdataMapper;
import com.dky.util.ChartsUtils;
import com.dky.util.ObjToArraysUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 16:10 2018/3/5
 */
public class DataOfNRainClusterThread implements Callable<String[]> {
    private Integer area;
    private String mdate;
    private LoadHisdataMapper loadHisdataMapper;
    private Integer loadvalueType;

    public DataOfNRainClusterThread() {
    }

    public DataOfNRainClusterThread(Integer area, String mdate,Integer loadvalueType,LoadHisdataMapper loadHisdataMapper) {
        this.area = area;
        this.mdate = mdate;
        this.loadHisdataMapper = loadHisdataMapper;
        this.loadvalueType = loadvalueType;
    }

    @Override
    public String[] call() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("area",area);
        params.put("mdate",mdate);
        params.put("loadvalueType",loadvalueType);
        params.put("clusterType",3);
        Point96 p1 = loadHisdataMapper.find96PointByNdateAndParams(params);
        if (p1 == null){
            return null;
        }
        return ObjToArraysUtils.getFieldValues(p1);
    }
}
