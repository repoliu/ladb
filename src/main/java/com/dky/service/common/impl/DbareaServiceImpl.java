package com.dky.service.common.impl;

import com.dky.entity.Dbarea;
import com.dky.mapper.DbareaMapper;
import com.dky.service.common.DbareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主要用于从数据库获得数据
 * @author YangSL
 */
@Service
public class DbareaServiceImpl implements DbareaService {
    private final DbareaMapper dbareaMapper;
    @Autowired
    public DbareaServiceImpl(DbareaMapper dbareaMapper){
        this.dbareaMapper = dbareaMapper;
    }
    public List<Dbarea> findAllArea() {
        return dbareaMapper.findAllArea();
    }

    @Override
    public List<Dbarea> findAreaByArea(int area) {
        return dbareaMapper.findAreaByArea(area);
    }

    @Override
    public String findAreanameByArea(int area) {
        return dbareaMapper.findAreaname(area);
    }
}
