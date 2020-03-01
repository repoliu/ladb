package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.influ.Qstation;
import com.dky.mapper.influ.QstationMapper;
import com.dky.service.influencingFactorAnalysis.QstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("qstationService")
public class QstationServiceImpl implements QstationService {

    @Autowired
    private QstationMapper qstationMapper;

    @Override
    public String findStcdByAreaname(String stnm) {
        return qstationMapper.findStcdByAreaname(stnm);
    }

    @Override
    public void insert(Qstation qstation) {
        qstationMapper.insert(qstation);
    }
}
