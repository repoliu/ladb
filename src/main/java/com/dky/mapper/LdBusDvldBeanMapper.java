package com.dky.mapper;

import com.dky.entity.LdBusDvld1;
import com.dky.entity.LdBusDvldBean;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sks on 2017/3/29.
 */
public interface LdBusDvldBeanMapper  {

    Set<String> selectPowerStationAll(String province);

    List<LdBusDvld1> selectLdBusDvldAll(String area);

    Set<String> selectPowerStation(String province);

    List<LdBusDvld1> selectLdBusDvld(Map<String,String> area);

}
