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

    private Integer cleaningMethod;

    private Date lastCleanDate;

    private Integer supportType;

    private Integer moduleNumber;

    private Float tiltAngle;

    private Float azimuthAngle;

    private Float dclineLength;

    private Float dclineUnitOhm;

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

    public Integer getCleaningMethod() {
        return cleaningMethod;
    }

    public void setCleaningMethod(Integer cleaningMethod) {
        this.cleaningMethod = cleaningMethod;
    }

    public Date getLastCleanDate() {
        return lastCleanDate;
    }

    public void setLastCleanDate(Date lastCleanDate) {
        this.lastCleanDate = lastCleanDate;
    }

    public Integer getSupportType() {
        return supportType;
    }

    public void setSupportType(Integer supportType) {
        this.supportType = supportType;
    }

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public Float getTiltAngle() {
        return tiltAngle;
    }

    public void setTiltAngle(Float tiltAngle) {
        this.tiltAngle = tiltAngle;
    }

    public Float getAzimuthAngle() {
        return azimuthAngle;
    }

    public void setAzimuthAngle(Float azimuthAngle) {
        this.azimuthAngle = azimuthAngle;
    }

    public Float getDclineLength() {
        return dclineLength;
    }

    public void setDclineLength(Float dclineLength) {
        this.dclineLength = dclineLength;
    }

    public Float getDclineUnitOhm() {
        return dclineUnitOhm;
    }

    public void setDclineUnitOhm(Float dclineUnitOhm) {
        this.dclineUnitOhm = dclineUnitOhm;
    }
}