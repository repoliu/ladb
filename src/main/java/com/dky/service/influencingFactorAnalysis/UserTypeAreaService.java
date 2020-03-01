package com.dky.service.influencingFactorAnalysis;

import com.dky.entity.UserTypeArea;

public interface UserTypeAreaService {
    UserTypeArea selectQuery(String areaname);
    void insert(UserTypeArea userTypeArea);
}
