package com.dky.mapper;

import com.dky.entity.BusloadType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusloadTypeMapper {


    List<BusloadType> selectData(BusloadType busloadType);
    List<BusloadType>  selectAllArea(@Param("busloadType")String  busloadType,@Param("listString") List<String> listString);


    Integer selectCount(BusloadType busloadType);
    Integer selectCountAreaAll(@Param("listArea")List<String> listArea,@Param("busloadType")String busloadType);
    Integer selectCountAreaAllSingle(@Param("dccDwscr")String dccDwscr,@Param("busloadType")String busloadType);


    void  insert(BusloadType busloadType);
    List<BusloadType> selectArea(@Param("dccDwscr")String   dccDwscr);

    int selectWDJY(@Param("dccDwscr")String   dccDwscr,@Param("one")String   one,@Param("two")String   two,@Param("three")String   three);
    int selectWDJYFather(@Param("listArea")List<String> listArea,@Param("one")String   one,@Param("two")String   two,@Param("three")String   three);
    int selectJY_KT(@Param("dccDwscr")String   dccDwscr,@Param("one")String   one,@Param("two")String   two,@Param("three")String   three);
    int selectJY_KTFather(@Param("listArea")List<String> listArea,@Param("one")String   one,@Param("two")String   two,@Param("three")String   three);
    int selectKTWD(@Param("dccDwscr")String   dccDwscr,@Param("one")String   one,@Param("two")String   two);
    int selectKTWDFather(@Param("listArea")List<String> listArea,@Param("one")String   one,@Param("two")String   two);

    int selectJiangYu(@Param("listArea")List<String> listArea,@Param("one")String   one,@Param("two")String   two);
    int selectJiangYuSingle(@Param("dccDwscr")String dccDwscr,@Param("one")String   one,@Param("two")String   two);

    int selectIntersection(@Param("listArea")List<String> listArea,@Param("one")String   one,@Param("two")String   two);

    int selectIntersectionSingle(@Param("dccDwscr")String dccDwscr,@Param("one")String   one,@Param("two")String   two);

}
