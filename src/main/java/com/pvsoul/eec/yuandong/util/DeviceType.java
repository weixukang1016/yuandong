package com.pvsoul.eec.yuandong.util;

import lombok.Getter;

@Getter
public enum DeviceType {

    ALL("全部", 0),
    PVSTRING("光伏组串", 1),
    COMBINER_BOX("汇流箱", 2),
    INVERTER("逆变器", 3),
    TRANSFORMER("升压变", 4);

    /** 枚举值 */
    private Integer deviceTypeCode;

    /** 枚举描述 */
    private String deviceType;

    DeviceType(String deviceType, int deviceTypeCode) {
        this.deviceType = deviceType;
        this.deviceTypeCode = deviceTypeCode;
    }

}
