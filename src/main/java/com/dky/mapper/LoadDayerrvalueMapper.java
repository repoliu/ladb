package com.dky.mapper;

import com.dky.entity.LoadDayerrvalue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadDayerrvalueMapper {



    LoadDayerrvalue selectAreaDate(@Param("area")int area,@Param("date")String date);
}
