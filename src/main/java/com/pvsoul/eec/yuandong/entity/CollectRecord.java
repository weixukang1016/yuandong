package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class CollectRecord {
    private Object id;

    private String sn;

    private Date collectTime;

    private Integer isRealtime;

    private String collectorVer;

    private Integer collectorType;

    private Integer rssiLevel;

    private Integer rssi;

    private Date createTime;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getIsRealtime() {
        return isRealtime;
    }

    public void setIsRealtime(Integer isRealtime) {
        this.isRealtime = isRealtime;
    }

    public String getCollectorVer() {
        return collectorVer;
    }

    public void setCollectorVer(String collectorVer) {
        this.collectorVer = collectorVer;
    }

    public Integer getCollectorType() {
        return collectorType;
    }

    public void setCollectorType(Integer collectorType) {
        this.collectorType = collectorType;
    }

    public Integer getRssiLevel() {
        return rssiLevel;
    }

    public void setRssiLevel(Integer rssiLevel) {
        this.rssiLevel = rssiLevel;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}