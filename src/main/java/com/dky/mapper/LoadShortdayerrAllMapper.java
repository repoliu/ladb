package com.dky.mapper;

import com.dky.entity.LdBusDvld1;
import com.dky.entity.LoadShortdayerrAll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lq on 2018/9/21.
 */
public interface LoadShortdayerrAllMapper {

    List<LoadShortdayerrAll> selectLoadShortdayerr(@Param("area")int area, @Param("mdate")String mdate);

}
