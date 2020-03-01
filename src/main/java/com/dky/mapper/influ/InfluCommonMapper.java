package com.dky.mapper.influ;

import com.dky.entity.influ.vo.ClusterData;
import com.dky.entity.period.Holiday;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: YangSL
 * @Description:
 * @Date: Create in 10:47 2018/2/27
 */
@Repository
public interface InfluCommonMapper {

    List<ClusterData> ListClusterDataByParams(Map<String,Object> params);

    void insertLoadWeatherClusterDataByList(List<ClusterData> result);

    void updateLoadWeatherClusterDataByEntity(ClusterData c);

    void deleteLoadWeatherClusterDateByList(Map<String,Object> params);

}
