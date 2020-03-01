package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.IndustryStructure;
import com.dky.entity.vo.ChartsOption;

import java.util.List;

public interface IndustryStructureService {
    List<ChartsOption> selectQuery(String area, String date, String nianXian);

    List<IndustryStructure> select();

    int insert(IndustryStructure industryStructure);

}
