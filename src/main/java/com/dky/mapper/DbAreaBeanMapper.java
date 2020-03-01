package com.dky.mapper;

import com.dky.entity.Dbarea;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * Created by sks on 2017/3/28.
 */
public interface DbAreaBeanMapper {

    Dbarea selectDbArea(HashMap datap);

    List<Dbarea> selectDbArea();

    Set<String>  selectPowerStation(String province);
}
