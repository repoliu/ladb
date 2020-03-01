package com.dky.service.define;

import com.dky.entity.DefineBean;

import java.util.List;

public interface DefineIndexService {

    List<DefineBean> findAll();

    DefineBean findAllById(String id);

    void insertDefine(DefineBean defineBean);
}
