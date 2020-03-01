package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.RelationSupport;
import com.dky.entity.vo.ChartsOption;


public interface RelationSupportService {


    int insert(RelationSupport relationSupport);
    ChartsOption selectQuery(String area, String date, String nianXian);


}
