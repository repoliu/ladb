package com.dky.service.generatrixAnalysis;


import com.dky.entity.MhSgDevPwrtransfmH1Mea;

import java.util.List;


/**
 * Created by lq on 2018/6/28
 */
public interface MhSgDevPwrtransfmH1MeaService {

    List<MhSgDevPwrtransfmH1Mea> selectBusParamHisdata(String tmpTableName, String id, String mdate);

    MhSgDevPwrtransfmH1Mea queryMaxDate(String tmpTableName);
}
