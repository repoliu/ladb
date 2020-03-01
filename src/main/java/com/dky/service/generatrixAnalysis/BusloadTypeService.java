package com.dky.service.generatrixAnalysis;

import com.dky.entity.BusloadType;
import com.dky.entity.vo.ChartsOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusloadTypeService {


    List<BusloadType> selectData(BusloadType busloadType);
    List<ChartsOption> selectData(String dccDwscr );
    List<ChartsOption> selectDataTwo(String dcc ,String dccDwscr );
    void  insert(BusloadType busloadType);
    List<BusloadType>  selectAllArea(String  busloadType,@Param("listString") List<String> listString);

}
