package com.dky.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DefineBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String defineId;
    private String appModule;
    private String funComponent;
    private String serviceName;
    private String serviceNumber;
    private String programName;
    private String operation;
    private String verTime;//数据录入时间

    public String getVerTime() {
        return verTime;
    }

    public void setVerTime(String verTime) {
        this.verTime = verTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDefineId() {
        return defineId;
    }

    public void setDefineId(String defineId) {
        this.defineId = defineId;
    }

    public String getAppModule() {
        return appModule;
    }

    public void setAppModule(String appModule) {
        this.appModule = appModule;
    }

    public String getFunComponent() {
        return funComponent;
    }

    public void setFunComponent(String funComponent) {
        this.funComponent = funComponent;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
