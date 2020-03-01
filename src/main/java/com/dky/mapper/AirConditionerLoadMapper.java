package com.dky.mapper;

import com.dky.entity.LoadAirconditionerAnalysis;
import com.dky.entity.RelationSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface AirConditionerLoadMapper {

    String findLastMdateByArea(Integer area);

    List<LoadAirconditionerAnalysis>  selectMaxProportion(@Param("area") int area, @Param("mdate") String mdate);

    List<LoadAirconditionerAnalysis>  selectMaxEnergyByDay(@Param("area") int area, @Param("mdate") String mdate);

    List<LoadAirconditionerAnalysis>  selectMaxEnergy(@Param("area") int area, @Param("mdate") String mdate);

    List<LoadAirconditionerAnalysis>  selectMaxLoad(@Param("area") int area, @Param("mdate") String mdate);

    List<LoadAirconditionerAnalysis>  selectMaxLoadByLoadDaytrait(@Param("area") int area, @Param("mdate") String mdate);

    List<LoadAirconditionerAnalysis>  selectMinLoadByLoadDaytrait(@Param("area") int area, @Param("mdate") String mdate);
}