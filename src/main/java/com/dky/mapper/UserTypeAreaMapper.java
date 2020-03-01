package com.dky.mapper;

import com.dky.entity.UserTypeArea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeAreaMapper {

    UserTypeArea selectQuery(UserTypeArea userTypeArea);
    void insert(UserTypeArea userTypeArea);

}
