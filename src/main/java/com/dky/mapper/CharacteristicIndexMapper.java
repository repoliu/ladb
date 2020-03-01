package com.dky.mapper;

import com.dky.entity.CharacteristicIndexBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacteristicIndexMapper {

    List<CharacteristicIndexBean> queryAllOne(String year);
    List<CharacteristicIndexBean> queryAllTwo(String year);
    List<CharacteristicIndexBean> queryAllThree(String year);

    List<CharacteristicIndexBean> queryAllByAreaAndYearOne(@Param("area")String area, @Param("year")String year);
    List<CharacteristicIndexBean> queryAllByAreaAndYearTwo(@Param("area")String area, @Param("year")String year);
    List<CharacteristicIndexBean> queryAllByAreaAndYearThree(@Param("area")String area, @Param("year")String year);

    String selectMaxDate();
}
