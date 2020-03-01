package com.dky.mapper;

import com.dky.entity.LoadHisdata96Cluster;
import com.dky.entity.vo.Point96;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoadHisdata96ClusterMapper {
    List<LoadHisdata96Cluster> findListByParams(Map<String, Object> params);

    Point96 findListExceptClusterTypeByParams(Map<String, Object> params);
    void insert(LoadHisdata96Cluster loadHisdata96Cluster);
}