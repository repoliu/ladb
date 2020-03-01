package com.dky.service.generatrixAnalysis.impl;

import com.dky.mapper.BusHisdataBeanMapper;
import com.dky.entity.BusHisdataBean;
import com.dky.service.generatrixAnalysis.BusHisdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sks on 2017/3/28.
 */
@Service("busHisdataService")
public class BusHisdataServiceImpl  implements BusHisdataService{


    @Autowired
    BusHisdataBeanMapper busHisdataBeanMapper;


    public List<BusHisdataBean> selectBusHisdata(String tmpTableName,String  id,String mdate) {
        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        map.put("id",id);
        map.put("mdate",mdate);
        return busHisdataBeanMapper.selectBusHisdata(map);
    }


    public List<BusHisdataBean> selectBusParamHisdata(String tmpTableName,String id,String mdate,String checkbox_fixedb,String checkbox_state) {

        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        map.put("id",id);
        map.put("mdate",mdate);

        String contion = "";
        if (checkbox_fixedb.equals("true") && !checkbox_state.equals("true")) {
            contion  = " and source='ss'";
        }
        /**
         * scada未选择并且rtnet选择
         */
        else if (!checkbox_fixedb.equals("true") && checkbox_state.equals("true")) {
            contion  = " and source='sr'";
        }
        /**
         * scada选择并且rtnet选择
         */
        else if (checkbox_fixedb.equals("true") && checkbox_state.equals("true")) {
            contion = " and (source='ss' or source='sr')";
        }

        map.put("contion",contion);

        return busHisdataBeanMapper.selectBusParamHisdata(map);
    }

    @Override
    public List<BusHisdataBean> selectBusHisdataByDate(String tmpTableName,String mdate, String province) {
        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        map.put("province",province);
        map.put("mdate",mdate);
        return busHisdataBeanMapper.selectHisdataByTime(map);
    }

    @Override
    public boolean compareCount(String tmpTableName, String mdate, String province) {
        boolean b=false;
        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        map.put("province",province);
        map.put("mdate",mdate);
       int noCluster=busHisdataBeanMapper.count(map);

        map.put("tmpTableName",tmpTableName+"_cluster");
       int cluster=busHisdataBeanMapper.count(map);
        if(noCluster==cluster){
            b=true;
        }
       return b;
    }

    @Override
    public Set<String> provinceList(String mdate,String tmpTableName) {
        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        map.put("mdate",mdate);
        return busHisdataBeanMapper.province(map);
    }


}
