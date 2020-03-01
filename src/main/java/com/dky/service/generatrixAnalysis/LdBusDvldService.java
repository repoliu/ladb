package com.dky.service.generatrixAnalysis;

import com.dky.entity.LdBusDvld1;
import com.dky.entity.LdBusDvldBean;

import java.util.List;
import java.util.Set;

/**
 * Created by lq on 2018/6/26.
 */
public interface LdBusDvldService {

    List<LdBusDvld1> selectLdBusDvldAll(String area);

    Set<String> powerStationListAll(String province);

    List<LdBusDvld1> selectLdBusDvld(String area);

    Set<String> powerStationList(String province);
}
