package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.PerYdl;
import com.dky.entity.vo.ChartsOption;

import java.util.List;

public interface PerYdlService {



    int insert(PerYdl perYdl);

    List<ChartsOption> selectQuery(String area, String date, String nianXian);

    List<ChartsOption> selectPerYdl (String area, String date, String nianXian);
}
