package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.PerIncome;
import com.dky.entity.vo.ChartsOption;

import java.util.List;

public interface PerIncomeService {
    int insert(PerIncome perIncome);
    List<ChartsOption> selectQuery(String area, String date, String nianXian);


}
