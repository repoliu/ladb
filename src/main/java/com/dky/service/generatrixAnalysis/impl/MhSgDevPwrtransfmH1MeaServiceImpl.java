package com.dky.service.generatrixAnalysis.impl;

import com.dky.entity.MhSgDevPwrtransfmH1Mea;
import com.dky.mapper.MhSgDevPwrtransfmH1MeaMapper;
import com.dky.service.generatrixAnalysis.MhSgDevPwrtransfmH1MeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lq on 2018/6/26.
 */
@Service("mhSgDevPwrtransfmH1MeaService")
public class MhSgDevPwrtransfmH1MeaServiceImpl implements MhSgDevPwrtransfmH1MeaService {


    @Autowired
    MhSgDevPwrtransfmH1MeaMapper selectBusParamHisdata;


    public List<MhSgDevPwrtransfmH1Mea> selectBusParamHisdata(String tmpTableName, String id, String mdate) {

        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        map.put("id",id);
        map.put("mdate",mdate);

        return selectBusParamHisdata.selectBusParamHisdata(map);
    }

    @Override
    public MhSgDevPwrtransfmH1Mea queryMaxDate(String tmpTableName) {

        HashMap<String,String> map =new HashMap<String,String>();
        map.put("tmpTableName",tmpTableName);
        return selectBusParamHisdata.selectMaxDate(map);
    }

}
