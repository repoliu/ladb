package com.dky.service.generatrixAnalysis.impl;

import com.dky.entity.LdBusDvld1;
import com.dky.mapper.LdBusDvld1Mapper;
import com.dky.service.generatrixAnalysis.LdBusDvld1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service("ldBusDvld1Service")
public class LdBusDvld1ServiceImpl implements LdBusDvld1Service {

    @Autowired
    private LdBusDvld1Mapper ldBusDvld1Mapper;

    @Override
    public List<LdBusDvld1> selectAll() {
        return ldBusDvld1Mapper.selectAll();
    }

    @Override
    public List<String[]> select() {
        return ldBusDvld1Mapper.select();
    }

    @Override
    public int selectCount(String dcc1Descr) {
        return ldBusDvld1Mapper.selectCount(dcc1Descr);
    }

    @Override
    public int selectCountDcc(String dcc,String dccNumber) {
        return ldBusDvld1Mapper.selectCountDcc(dcc,dccNumber);
    }

    @Override
    public int selectCountDcc3(String dccNumber) {
        return ldBusDvld1Mapper.selectCountDcc3(dccNumber);
    }

    @Override
    public int selectCountDcc4( String dccNumber) {
        return ldBusDvld1Mapper.selectCountDcc4(dccNumber);
    }

    @Override
    public int selectCountDcc5(String dccNumber) {
        return ldBusDvld1Mapper.selectCountDcc5(dccNumber);
    }
    @Override
    public List<LdBusDvld1> selectTreeTwo(String dcc, String dccDwscr) {
        List<LdBusDvld1> listLdbus = new LinkedList<>();
        if (dcc.equals("dcc2_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue2(dccDwscr);
        } else if (dcc.equals("dcc3_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue3(dccDwscr);
        } else if (dcc.equals("dcc4_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue4(dccDwscr);
        } else if (dcc.equals("dcc5_descr")) {
            listLdbus = ldBusDvld1Mapper.selectValue5(dccDwscr);
        }
        return listLdbus;
    }

    @Override
    public int selectCountDcc1(String dcc1Descr) {
        return ldBusDvld1Mapper.selectCountDcc1(dcc1Descr);
    }
}
