package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.influ.Qstation;

import java.util.List;

public interface QstationService {


    String findStcdByAreaname(String stnm);
    void insert(Qstation qstation);

}
