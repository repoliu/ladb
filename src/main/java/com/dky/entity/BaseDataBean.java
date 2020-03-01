package com.dky.entity;

import java.io.Serializable;

/**
 * 基础数据POJO
 * Created by henry on 2017/3/13.
 */
public class BaseDataBean extends BasicBean implements Serializable{


    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
