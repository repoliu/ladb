package com.dky.mapper;

import com.dky.entity.LoadForesult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoadForesultMapper {
    LoadForesult findForesultByParams(Map<String, Object> params);

    LoadForesult select(LoadForesult loadForesult);
    List<LoadForesult> selectAll();
    int update(LoadForesult loadForesult);
    int insert(LoadForesult loadForesult);
    LoadForesult selectContrastCurve(@Param("area")int area, @Param("mdate")String mdate);

}