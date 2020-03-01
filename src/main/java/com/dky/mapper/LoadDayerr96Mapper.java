package com.dky.mapper;

import com.dky.entity.LoadDayerr96;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoadDayerr96Mapper {



   List<LoadDayerr96> selectAreaDate(@Param("area")String area, @Param("date")String date);
}
