package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class PvStringRealCurrentModel {
    private Object id;

    private Object realModelId;

    private Date createTime;

    private Date updateTime;

    private Boolean isValid;

    private Integer valueIndex;

    private Float modelValue;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getRealModelId() {
        return realModelId;
    }

    public void setRealModelId(Object realModelId) {
        this.realModelId = realModelId;
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

    public Integer getValueIndex() {
        return valueIndex;
    }

    public void setValueIndex(Integer valueIndex) {
        this.valueIndex = valueIndex;
    }

    public Float getModelValue() {
        return modelValue;
    }

    public void setModelValue(Float modelValue) {
        this.modelValue = modelValue;
    }
}