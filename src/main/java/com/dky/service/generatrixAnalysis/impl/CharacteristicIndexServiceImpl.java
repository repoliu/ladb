package com.dky.service.generatrixAnalysis.impl;

import com.dky.entity.CharacteristicIndexBean;
import com.dky.mapper.CharacteristicIndexMapper;
import com.dky.service.generatrixAnalysis.CharacteristicIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CharacteristicIndexService")
public class CharacteristicIndexServiceImpl implements CharacteristicIndexService {

    @Autowired
    private CharacteristicIndexMapper characteristicIndexMapper;

    @Override
    public List<CharacteristicIndexBean> findAllOne(String year) {
        return characteristicIndexMapper.queryAllOne(year);
    }

    @Override
    public List<CharacteristicIndexBean> findAllTwo(String year) {
        return characteristicIndexMapper.queryAllTwo(year);
    }

    @Override
    public List<CharacteristicIndexBean> findAllThree(String year) {
        return characteristicIndexMapper.queryAllThree(year);
    }

    @Override
    public List<CharacteristicIndexBean> findAllByAreaAndYearOne(String area, String year) {
        return characteristicIndexMapper.queryAllByAreaAndYearOne(area, year);
    }

    @Override
    public List<CharacteristicIndexBean> findAllByAreaAndYearTwo(String area, String year) {
        return characteristicIndexMapper.queryAllByAreaAndYearTwo(area, year);
    }

    @Override
    public List<CharacteristicIndexBean> findAllByAreaAndYearThree(String area, String year) {
        return characteristicIndexMapper.queryAllByAreaAndYearThree(area, year);
    }

    @Override
    public String findMaxDate() {
        return characteristicIndexMapper.selectMaxDate();
    }
}
