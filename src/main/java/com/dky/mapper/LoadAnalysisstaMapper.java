package com.dky.mapper;

import com.dky.entity.LoadAnalysissta;
import com.dky.entity.LoadAnalysisstaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadAnalysisstaMapper {
    int insert(LoadAnalysissta record);
    LoadAnalysissta selectByPrimaryKey(LoadAnalysisstaKey key);
}