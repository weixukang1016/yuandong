package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class TransformerData {
    private Object id;

    private Object powerStationId;

    private Object transformerId;

    private Float lU;

    private Float hU;

    private Float fac;

    private Float pac;

    private Float pFactor;

    private Date createTime;

    private Date deviceTime;

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

    public Object getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(Object transformerId) {
        this.transformerId = transformerId;
    }

    public Float getlU() {
        return lU;
    }

    public void setlU(Float lU) {
        this.lU = lU;
    }

    public Float gethU() {
        return hU;
    }

    public void sethU(Float hU) {
        this.hU = hU;
    }

    public Float getFac() {
        return fac;
    }

    public void setFac(Float fac) {
        this.fac = fac;
    }

    public Float getPac() {
        return pac;
    }

    public void setPac(Float pac) {
        this.pac = pac;
    }

    public Float getpFactor() {
        return pFactor;
    }

    public void setpFactor(Float pFactor) {
        this.pFactor = pFactor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Date deviceTime) {
        this.deviceTime = deviceTime;
    }
}