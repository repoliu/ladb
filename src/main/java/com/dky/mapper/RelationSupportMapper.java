package com.dky.mapper;

import com.dky.entity.RelationSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Repository
public interface RelationSupportMapper {

    int insert(RelationSupport relationSupport);

    List<RelationSupport> findSupportListByKey(Map<String, Object> param);


    List<RelationSupport> selectQuery(RelationSupport relationSupport);


    String findLastMdateByArea(Integer area);

    List<RelationSupport> findYearSupportByParams(Map<String, Object> params);

    BigDecimal findValueByParams(Map<String, Object> params);

    List<String> findLowDateListByParams(Map<String, Object> params);

    List<RelationSupport> findRainstormSupportByParams(Map<String, Object> params);


    List<RelationSupport>  selectTypeX(@Param("area")int area,@Param("startYear")String  startYear,@Param("endYear")String  endYear,@Param("type")String  type);
}