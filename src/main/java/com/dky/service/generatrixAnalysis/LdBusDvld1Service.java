package com.dky.service.generatrixAnalysis;

import com.dky.entity.LdBusDvld1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LdBusDvld1Service {
    List<LdBusDvld1> selectAll();
    List<String []> select();
    int selectCount(@Param("dcc1Descr")String dcc1Descr);
    int selectCountDcc(String dcc,String dccNumber);
    int selectCountDcc1(@Param("dcc1Descr")String dcc1Descr);
    int selectCountDcc3(@Param("dccNumber")String dccNumber);
    int selectCountDcc4(@Param("dccNumber")String dccNumber);
    int selectCountDcc5(@Param("dccNumber")String dccNumber);
    List<LdBusDvld1>  selectTreeTwo(String dcc, String dccDwscr);
}
