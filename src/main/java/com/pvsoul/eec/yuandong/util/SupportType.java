package com.pvsoul.eec.yuandong.util;

import lombok.Getter;

/**
 * 倾角类型
 */
@Getter
public enum SupportType {

    FIXED("fixed", 1, "固定倾角"),
    TRACKING("tracking", 2, "跟踪式");

    /** 清洁方式 */
    private String type;

    /** 清洁方式值 */
    private Integer code;

    /** 文字描述 */
    private String description;

    SupportType(String type, int code, String description) {
        this.type = type;
        this.code = code;
        this.description = description;
    }
}
