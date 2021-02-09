package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class Inverter {
    private Object id;

    private Object powerStationId;

    private String inverterSn;

    private String inverterModel;

    private String manufactor;

    private Float ratedPower;

    private String inverterVer;

    private String region;

    private Date createTime;

    private Date updateTime;

    private String inverterNo;

    private Boolean isValid;

    private Integer status;

    private Date statusTime;

    private Object transformerId;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getPowerStationId() {
        return powerStationId;
    }

    public void setPowerStationId(Object powerStationId) {
        this.powerStationId = powerStationId;
    }

    public String getInverterSn() {
        return inverterSn;
    }

    public void setInverterSn(String inverterSn) {
        this.inverterSn = inverterSn;
    }

    public String getInverterModel() {
        return inverterModel;
    }

    public void setInverterModel(String inverterModel) {
        this.inverterModel = inverterModel;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public Float getRatedPower() {
        return ratedPower;
    }

    public void setRatedPower(Float ratedPower) {
        this.ratedPower = ratedPower;
    }

    public String getInverterVer() {
        return inverterVer;
    }

    public void setInverterVer(String inverterVer) {
        this.inverterVer = inverterVer;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInverterNo() {
        return inverterNo;
    }

    public void setInverterNo(String inverterNo) {
        this.inverterNo = inverterNo;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public Object getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(Object transformerId) {
        this.transformerId = transformerId;
    }
}