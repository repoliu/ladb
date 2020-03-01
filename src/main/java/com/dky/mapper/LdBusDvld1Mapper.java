package com.dky.mapper;

import com.dky.entity.LdBusDvld1;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LdBusDvld1Mapper {




    List<String []> select();

    List<LdBusDvld1> selectAll();

    int selectCount(@Param("dcc1Descr")String dcc1Descr);
    int selectCountAreaAll(@Param("listArea")List<String> listArea);

    int selectCountDcc(@Param("dcc")String dcc,@Param("dccNumber")String dccNumber);
    int selectCountDcc1(@Param("dcc1Descr")String dcc1Descr);
    int selectCountDcc2(@Param("dccNumber")String dccNumber);
    int selectCountDcc3(@Param("dccNumber")String dccNumber);
    int selectCountDcc4(@Param("dccNumber")String dccNumber);
    int selectCountDcc5(@Param("dccNumber")String dccNumber);

    List<LdBusDvld1> selectValue(@Param("dcc")String dcc,@Param("dccName")String dccName);
    List<LdBusDvld1> selectValue2(@Param("dccName")String dccName);
    List<LdBusDvld1> selectValue3(@Param("dccName")String dccName);
    List<LdBusDvld1> selectValue4(@Param("dccName")String dccName);
    List<LdBusDvld1> selectValue5(@Param("dccName")String dccName);
}
