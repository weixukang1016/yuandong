package com.pvsoul.eec.yuandong.util;

import lombok.Getter;

@Getter
public enum DeviceStatus {

    NORMAL("正常", 0),
    FAULT("故障", 1),
    LOW_EFFICIENCY("低效", 2);

    /** 枚举值 */
    private Integer deviceStatusCode;

    /** 枚举描述 */
    private String deviceStatus;

    DeviceStatus(String deviceStatus, int deviceStatusCode) {
        this.deviceStatus = deviceStatus;
        this.deviceStatusCode = deviceStatusCode;
    }

    public String getStatusByCode(int deviceStatusCode) {
        if (deviceStatusCode == NORMAL.deviceStatusCode) {
            return NORMAL.deviceStatus;
        } else if (deviceStatusCode == FAULT.deviceStatusCode) {
            return FAULT.deviceStatus;
        } else if (deviceStatusCode == LOW_EFFICIENCY.deviceStatusCode) {
            return LOW_EFFICIENCY.deviceStatus;
        }
        return null;
    }
}
