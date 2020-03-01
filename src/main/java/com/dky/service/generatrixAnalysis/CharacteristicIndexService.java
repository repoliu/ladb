package com.dky.service.generatrixAnalysis;

import com.dky.entity.CharacteristicIndexBean;

import java.util.List;

public interface CharacteristicIndexService {

    List<CharacteristicIndexBean> findAllOne(String year);
    List<CharacteristicIndexBean> findAllTwo(String year);
    List<CharacteristicIndexBean> findAllThree(String year);

    List<CharacteristicIndexBean> findAllByAreaAndYearOne(String area, String year);
    List<CharacteristicIndexBean> findAllByAreaAndYearTwo(String area, String year);
    List<CharacteristicIndexBean> findAllByAreaAndYearThree(String area, String year);

    String findMaxDate();
}
