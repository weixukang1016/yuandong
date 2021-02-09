package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class TemperatureSensor {
    private Object id;

    private Object powerStationId;

    private Object inverterId;

    private Object pvStringId;

    private String collectorDeviceId;

    private Integer sensorIndex;

    private Date createTime;

    private Date updateTime;

    private Boolean isValid;

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

    public Object getPvStringId() {
        return pvStringId;
    }

    public void setPvStringId(Object pvStringId) {
        this.pvStringId = pvStringId;
    }

    public String getCollectorDeviceId() {
        return collectorDeviceId;
    }

    public void setCollectorDeviceId(String collectorDeviceId) {
        this.collectorDeviceId = collectorDeviceId;
    }

    public Integer getSensorIndex() {
        return sensorIndex;
    }

    public void setSensorIndex(Integer sensorIndex) {
        this.sensorIndex = sensorIndex;
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Object getTransformerId() {
        return transformerId;
    }

    public void setTransformerId(Object transformerId) {
        this.transformerId = transformerId;
    }
}