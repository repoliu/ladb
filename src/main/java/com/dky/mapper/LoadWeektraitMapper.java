package com.dky.mapper;

import com.dky.entity.LoadWeektrait;
import com.dky.entity.LoadWeektraitKey;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadWeektraitMapper {
    LoadWeektrait selectByPrimaryKey(LoadWeektraitKey key);
}