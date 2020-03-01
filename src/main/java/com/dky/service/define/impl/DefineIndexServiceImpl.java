package com.dky.service.define.impl;

import com.dky.entity.DefineBean;
import com.dky.mapper.DefineIndexMapper;
import com.dky.service.define.DefineIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DefineIndexService")
public class DefineIndexServiceImpl implements DefineIndexService{

    @Autowired
    private DefineIndexMapper defineIndexMapper;

    @Override
    public List<DefineBean> findAll() {
        return defineIndexMapper.queryAll();
    }

    @Override
    public DefineBean findAllById(String id) {
        return defineIndexMapper.queryAllById(id);
    }

    @Override
    public void insertDefine(DefineBean defineBean) {
        defineIndexMapper.insertDefineBean(defineBean);
    }
}
