package com.dky.mapper;

import com.dky.entity.DefineBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefineIndexMapper {

    List<DefineBean> queryAll();

    DefineBean queryAllById(String id);

    void insertDefineBean(DefineBean defineBean);
}
