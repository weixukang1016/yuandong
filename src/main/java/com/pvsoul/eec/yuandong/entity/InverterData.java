package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class InverterData {
    private Object id;

    private Object collectRecordId;

    private String inverterSn;

    private String inverterModel;

    private Float ratedPower;

    private String inverterVer;

    private Float eToday;

    private Float eMonth;

    private Float eYear;

    private Float eTotal;

    private Float inverterTemp;

    private String state;

    private String alarmCn;

    private String alarmEn;

    private Float fac;

    private Float pac;

    private Integer nationalCode;

    private String national;

    private Date createTime;

    private Object powerStationId;

    private Object transformerId;

    private Object inverterId;

    private Date deviceTime;

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

    public Float geteToday() {
        return eToday;
    }

    public void seteToday(Float eToday) {
        this.eToday = eToday;
    }

    public Float geteMonth() {
        return eMonth;
    }

    public void seteMonth(Float eMonth) {
        this.eMonth = eMonth;
    }

    public Float geteYear() {
        return eYear;
    }

    public void seteYear(Float eYear) {
        this.eYear = eYear;
    }

    public Float geteTotal() {
        return eTotal;
    }

    public void seteTotal(Float eTotal) {
        this.eTotal = eTotal;
    }

    public Float getInverterTemp() {
        return inverterTemp;
    }

    public void setInverterTemp(Float inverterTemp) {
        this.inverterTemp = inverterTemp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAlarmCn() {
        return alarmCn;
    }

    public void setAlarmCn(String alarmCn) {
        this.alarmCn = alarmCn;
    }

    public String getAlarmEn() {
        return alarmEn;
    }

    public void setAlarmEn(String alarmEn) {
        this.alarmEn = alarmEn;
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

    public Integer getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Integer nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Object getInverterId() {
        return inverterId;
    }

    public void setInverterId(Object inverterId) {
        this.inverterId = inverterId;
    }

    public Date getDeviceTime() {
        return deviceTime;
    }

    public void setDeviceTime(Date deviceTime) {
        this.deviceTime = deviceTime;
    }
}