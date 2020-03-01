package com.dky.service.generatrixAnalysis;

import com.dky.entity.BusloadTypeSupport;
import com.dky.entity.vo.ChartsOption;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusloadTypeSupportService {

    ChartsOption selectAreaDate(String  createDate, String busloadType, String dccDescr);
    ChartsOption   selectListAreaDate(String  createDate, String busloadType,List<String> listArea);

    String selectMaxDate(List<String> listArea);
}
