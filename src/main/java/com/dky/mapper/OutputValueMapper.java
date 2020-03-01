package com.dky.mapper;

import com.dky.entity.OutputValue;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OutputValueMapper {
    int insert (OutputValue outputValue);

    List<OutputValue> selectQuery(OutputValue outputValue);

    Map selectMaxDate(OutputValue outputValue);

}
