package com.dky.mapper.influ;

import com.dky.entity.influ.LoadRainInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRainInfoMapper {
    List<LoadRainInfo> select (LoadRainInfo loadRainInfo);
    List<LoadRainInfo> selectCopy (@Param("areaname")String areaname,@Param("rainLeve")String  rainLeve,@Param("stringDate")String stringDate);

    List<LoadRainInfo> selectDayNumber(LoadRainInfo loadRainInfo);
    void delete (LoadRainInfo loadRainInfo);
    void deletes (@Param("stringDate")String stringDate,@Param("areaname")String areaname,@Param("model")String model);

    void insert (List<LoadRainInfo> list);

}
