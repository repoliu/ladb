package com.dky.mapper;

import com.dky.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoadWeektypeload96Mapper {

    LoadWeektypeload96 selectByPrimaryKey(LoadWeektypeload96Key key);

    List<LoadWeektypeload96> selectForWeek96(LoadWeektypeload96 key);
    String week96MaxEndDay(LoadWeektypeload96Key key);

    int insert(LoadMonthtypeload96 loadMonthtypeload96);
    void insert(LoadWeektypeload96 loadWeektypeload96);

}