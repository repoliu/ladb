package com.dky.mapper;

import com.dky.entity.MhSgDevPwrtransfmH1Mea;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lq on 2018/6/28.
 */
public interface MhSgDevPwrtransfmH1MeaMapper {

    List<MhSgDevPwrtransfmH1Mea> selectBusParamHisdata(HashMap datamap);

    MhSgDevPwrtransfmH1Mea selectMaxDate(HashMap datamap);

}
