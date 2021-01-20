package com.pvsoul.eec.yuandong.entity;

import java.util.Date;

public class MeteorologicalData {
    private Object id;

    private String deviceId;

    private Float temperature;

    private Integer rainFall;

    private Float pressure;

    private Float humidity;

    private Integer windDirection;

    private Float windSpeed;

    private Float irradiance;

    private Date createTime;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getRainFall() {
        return rainFall;
    }

    public void setRainFall(Integer rainFall) {
        this.rainFall = rainFall;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Float getIrradiance() {
        return irradiance;
    }

    public void setIrradiance(Float irradiance) {
        this.irradiance = irradiance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}