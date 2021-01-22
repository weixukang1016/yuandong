package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class InverterPvData {
    private Object id;

    private Object collectRecordId;

    private Object inverterDataId;

    private Float u;

    private Float i;

    private Date createTime;

    private Integer pvIndex;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getCollectRecordId() {
        return collectRecordId;
    }

    public void setCollectRecordId(Object collectRecordId) {
        this.collectRecordId = collectRecordId;
    }

    public Object getInverterDataId() {
        return inverterDataId;
    }

    public void setInverterDataId(Object inverterDataId) {
        this.inverterDataId = inverterDataId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPvIndex() {
        return pvIndex;
    }

    public void setPvIndex(Integer pvIndex) {
        this.pvIndex = pvIndex;
    }
}