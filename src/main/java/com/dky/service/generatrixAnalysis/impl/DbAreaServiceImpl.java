package com.dky.service.generatrixAnalysis.impl;

import com.dky.mapper.DbAreaBeanMapper;
import com.dky.entity.Dbarea;
import com.dky.service.generatrixAnalysis.DbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sks on 2017/3/28.
 */
@Service("dbAreaService")
public class DbAreaServiceImpl implements DbAreaService {

    @Autowired
    DbAreaBeanMapper dbAreaBeanMapper;
    private String datasource="dataSource2";
    public Dbarea selectDbArea(HashMap datap) {
        return dbAreaBeanMapper.selectDbArea(datap);
    }


    public List<Dbarea> selectDbArea() {
        return dbAreaBeanMapper.selectDbArea();
    }

    @Override
    public Set<String> powerStationList(String province) {
        return dbAreaBeanMapper.selectPowerStation(province);
    }


}
