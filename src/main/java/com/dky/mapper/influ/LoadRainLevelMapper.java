package com.dky.mapper.influ;

import com.dky.entity.influ.LoadRainLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRainLevelMapper {
    LoadRainLevel select(@Param("levelName") String levelName);
    List<LoadRainLevel> selectAll();
    void insert(LoadRainLevel loadRainLevel);

    LoadRainLevel selectMin(@Param("levelName") String levelName);
}
