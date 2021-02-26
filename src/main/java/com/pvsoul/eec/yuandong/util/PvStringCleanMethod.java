package com.pvsoul.eec.yuandong.util;

import lombok.Getter;

/**
 * 清洁方式
 */
@Getter
public enum PvStringCleanMethod {

    NONE("none", 0, "不清洗"),
    NANUAL("manual", 1, "手工清洗"),
    AUTO("auto", 2, "自动清洗");

    /** 清洁方式 */
    private String method;

    /** 清洁方式值 */
    private Integer code;

    /** 文字描述 */
    private String description;

    PvStringCleanMethod(String method, int code, String description) {
        this.method = method;
        this.code = code;
        this.description = description;
    }

}
