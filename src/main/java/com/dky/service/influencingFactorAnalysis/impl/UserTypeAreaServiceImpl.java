package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.UserTypeArea;
import com.dky.mapper.UserTypeAreaMapper;
import com.dky.service.influencingFactorAnalysis.UserTypeAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userTypeAreaService")
public class UserTypeAreaServiceImpl implements UserTypeAreaService {

@Autowired
private UserTypeAreaMapper userTypeAreaMapper;
    public UserTypeArea selectQuery(String areaname) {
        UserTypeArea userTypeArea=new UserTypeArea();
        userTypeArea.setUserName(areaname);
        return userTypeAreaMapper.selectQuery(userTypeArea);
    }

    @Override
    public void insert(UserTypeArea userTypeArea) {
        userTypeAreaMapper.insert(userTypeArea);

    }
}
