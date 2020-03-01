package com.dky.entity.vo;

import java.io.Serializable;


/**
 * 这个时母线分类查询数据的返回类型，数据库没这个表
 * layui需要的类型
 */
public class BusReturnValue  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String devDescr;//负荷名称
    private String typeDegree;//设备类型的支持度


    public String getDevDescr() {
        return devDescr;
    }

    public void setDevDescr(String devDescr) {
        this.devDescr = devDescr;
    }

    public String getTypeDegree() {
        return typeDegree;
    }

    public void setTypeDegree(String typeDegree) {
        this.typeDegree = typeDegree;
    }
}
