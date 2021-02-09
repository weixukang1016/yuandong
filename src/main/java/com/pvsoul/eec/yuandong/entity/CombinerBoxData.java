package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class CombinerBoxData {
    private Object id;

    private Object powerStationId;

    private Object inverterId;

    private Object combinerBoxId;

    private Float u;

    private Float i;

    private Float temperature;

    private Date createTime;

    private Date deviceTime;

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

    public Object getCombinerBoxId() {
        return combinerBoxId;
    }

    public void setCombinerBoxId(Object combinerBoxId) {
        this.combinerBoxId = combinerBoxId;
    }

    public Float getU() {
        return u;
    }

    public void setU(Float u) {
        this.u = u;
    }

    public Float getI() {
        return i;
    }

    public void setI(Float i) {
        this.i = i;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
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

    public Object getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(Object transformerId) {
        this.transformerId = transformerId;
    }
}