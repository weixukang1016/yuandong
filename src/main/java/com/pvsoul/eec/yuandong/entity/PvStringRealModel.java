package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class PvStringRealModel {
    private Object id;

    private Object pvStringId;

    private Date createTime;

    private Date updateTime;

    private Boolean isValid;

    private Float degradationRatio;

    private Float soilingRatio;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getPvStringId() {
        return pvStringId;
    }

    public void setPvStringId(Object pvStringId) {
        this.pvStringId = pvStringId;
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

    public Float getDegradationRatio() {
        return degradationRatio;
    }

    public void setDegradationRatio(Float degradationRatio) {
        this.degradationRatio = degradationRatio;
    }

    public Float getSoilingRatio() {
        return soilingRatio;
    }

    public void setSoilingRatio(Float soilingRatio) {
        this.soilingRatio = soilingRatio;
    }
}