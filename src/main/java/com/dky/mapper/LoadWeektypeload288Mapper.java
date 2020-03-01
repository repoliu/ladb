package com.dky.mapper;

import com.dky.entity.LoadWeektypeload288;
import com.dky.entity.LoadWeektypeload288Key;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoadWeektypeload288Mapper {
    int insert(LoadWeektypeload288 record);
    LoadWeektypeload288 selectByPrimaryKey(LoadWeektypeload288Key key);
    List<LoadWeektypeload288> selectForWeek288(LoadWeektypeload288 key);
    String week288MaxEndDay(LoadWeektypeload288Key key);
}