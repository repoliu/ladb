package com.dky.mapper;

import com.dky.entity.LoadYeartrait;
import com.dky.entity.LoadYeartraitKey;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadYeartraitMapper {
    LoadYeartrait selectByPrimaryKey(LoadYeartraitKey key);
}