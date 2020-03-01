package com.dky.mapper;

import com.dky.entity.LoadHisdata;
import com.dky.entity.LoadHisdataKey;
import com.dky.entity.vo.Point96;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface LoadHisdataMapper {
    int insert(LoadHisdata record);
    LoadHisdata selectByPrimaryKey(LoadHisdataKey key);
    List<LoadHisdata> selectQuery96(LoadHisdataKey key);//XLY
    List<LoadHisdata> selectQuery288(LoadHisdataKey key);//XLY
    String selectOne(LoadHisdataKey key);//XLY
    String selectTwo(LoadHisdataKey key);//XLY
    List<LoadHisdata> selectAll(LoadHisdataKey key);//XLY


    LoadHisdata selectByAreaAndMdate(LoadHisdataKey loadHisdataKey);

    List<LoadHisdata> find288MdateListByKey(LoadHisdataKey loadHisdataKey);

    List<LoadHisdata> find96MdateListByKey(LoadHisdataKey loadHisdataKey);

    LoadHisdata selectDayByParams(Map<String, Object> params);

    List<LoadHisdata> findListByParams(Map<String, Object> params);

    List<LoadHisdata> findOnly96ListByAreaAndTimearea(Integer area, String startday, String endday);

    Point96 find96PointByNdateAndParams(Map<String, Object> params);
    List<LoadHisdata> selectLoadHisdataToLoadDaytrait(@Param("area")int area, @Param("mdate")String mdate);

    LoadHisdata selectContrastCurve(@Param("area")int area, @Param("mdate")String mdate);
}