package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.PerGdp;
import com.dky.entity.vo.ChartsOption;

import java.util.List;

public interface PerGdpService {
    int insert(PerGdp perGdp);
    List<ChartsOption> selectQuery(String area, String date, String nianXian);

}
