package com.dky.mapper;

import com.dky.entity.BusloadTypeSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BusloadTypeSupportMapper {
    List<BusloadTypeSupport> selectAreaDate(@Param("createDate")String  createDate, @Param("busloadType")String busloadType, @Param("dccDescr")String dccDescr);
    List<BusloadTypeSupport>   selectListAreaDate(@Param("createDate")String  createDate, @Param("busloadType")String busloadType, @Param("listArea")List<String> listArea );

    String selectMaxDate(@Param("listArea")List<String> listArea);

}
