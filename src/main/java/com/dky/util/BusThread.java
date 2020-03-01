package com.dky.util;

import com.dky.mapper.BusloadTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BusThread extends Thread {



    public  int  oneNumber(List<String> listArea ,String one ,String two,String three,BusloadTypeMapper busloadTypeMapper){
        return (selectKTWDFather(listArea,one,two,busloadTypeMapper)-selectJY_KTFather(listArea,one,two,three,busloadTypeMapper));
    }

    public  int  twoNumber(List<String> listArea ,String one ,String two,String three,BusloadTypeMapper busloadTypeMapper){
        return selectWDJYFather(listArea,one,two,three,busloadTypeMapper);
    }
    public  int  threeNumber(List<String> listArea ,String one ,String two,String three,BusloadTypeMapper busloadTypeMapper){
        return selectWDJYFather(listArea,one,two,three,busloadTypeMapper);
    }
    public  int  fourNumber(List<String> listArea ,String one ,String two,BusloadTypeMapper busloadTypeMapper){
        return selectKTWDFather(listArea,one,two,busloadTypeMapper);
    }
    public  int  fiveNumber(List<String> listArea ,String one ,String two,String three,BusloadTypeMapper busloadTypeMapper){
        return selectJY_KTFather(listArea,one,two,three,busloadTypeMapper);
    }




    public int selectKTWDFather(List<String> listArea ,String one ,String two,BusloadTypeMapper busloadTypeMapper){
        return busloadTypeMapper.selectKTWDFather(listArea, one, two);
    }



    public int selectJY_KTFather(List<String> listArea ,String one ,String two,String three,BusloadTypeMapper busloadTypeMapper){
        return busloadTypeMapper.selectJY_KTFather(listArea, one, two,three);
    }


    public int selectWDJYFather(List<String> listArea ,String one ,String two,String three,BusloadTypeMapper busloadTypeMapper){
        return busloadTypeMapper.selectWDJYFather(listArea, one, two,three);
    }













}
