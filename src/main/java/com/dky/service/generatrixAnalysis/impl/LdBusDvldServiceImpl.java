package com.dky.service.generatrixAnalysis.impl;

import com.dky.entity.LdBusDvld1;
import com.dky.mapper.LdBusDvldBeanMapper;
import com.dky.service.generatrixAnalysis.LdBusDvldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lq on 2018/6/26.
 */
@Service("ldBusDvldService")
public class LdBusDvldServiceImpl implements LdBusDvldService {

    @Autowired
    LdBusDvldBeanMapper ldBusDvldBeanMapper;

    @Override
    public List<LdBusDvld1> selectLdBusDvldAll(String area) {
        return ldBusDvldBeanMapper.selectLdBusDvldAll(area);
    }

    @Override
    public Set<String> powerStationListAll(String province) {
        return ldBusDvldBeanMapper.selectPowerStationAll(province);
    }

    public List<LdBusDvld1> selectLdBusDvld(String area) {
        Map<String,String> areaMap=new HashMap<>();
        areaMap.put("area",area);
        return ldBusDvldBeanMapper.selectLdBusDvld(areaMap);
    }

    public Set<String> powerStationList(String province) {
        return ldBusDvldBeanMapper.selectPowerStation(province);
    }
}
