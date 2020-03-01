package com.dky.entity;

import java.io.Serializable;

public class UserTypeArea  implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer num;//这就是记录了SocietyCapacity实体类的id

    private String userId;

    private String userName;

    private Integer parentId;

    public Integer getNum() {        return num;    }
    public void setNum(Integer num) {        this.num = num;    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getParentId() {        return parentId;    }
    public void setParentId(Integer parentId) {        this.parentId = parentId;    }
}
