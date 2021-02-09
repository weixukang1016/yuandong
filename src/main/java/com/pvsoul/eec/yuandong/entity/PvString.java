package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class PvString {
    private Object id;

    private Object powerStationId;

    private Object inverterId;

    private String stringNo;

    private Date createTime;

    private Date updateTime;

    private Integer pvIndex;

    private Boolean isStandard;

    private Boolean isValid;

    private Object combinerBoxId;

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

    public Object getInverterId() {
        return inverterId;
    }

    public void setInverterId(Object inverterId) {
        this.inverterId = inverterId;
    }

    public String getStringNo() {
        return stringNo;
    }

    public void setStringNo(String stringNo) {
        this.stringNo = stringNo;
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

    public Integer getPvIndex() {
        return pvIndex;
    }

    public void setPvIndex(Integer pvIndex) {
        this.pvIndex = pvIndex;
    }

    public Boolean getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(Boolean isStandard) {
        this.isStandard = isStandard;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Object getCombinerBoxId() {
        return combinerBoxId;
    }

    public void setCombinerBoxId(Object combinerBoxId) {
        this.combinerBoxId = combinerBoxId;
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