package com.dky.mapper;

import com.dky.entity.SocietyCapacity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SocietyCapacityMapper {


    int insert(SocietyCapacity societyCapacity);

    List<SocietyCapacity> selectQuery(SocietyCapacity societyCapacity);//这个是多条件查询的

    Map selectMaxDate(SocietyCapacity societyCapacity);//这个是加载的时候获取最大时间的
}
