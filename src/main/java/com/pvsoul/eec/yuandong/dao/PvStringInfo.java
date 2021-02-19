package com.pvsoul.eec.yuandong.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PvStringInfo {

    private  String id;

    private String stringNo;

    private int status;

    private boolean isStandard;

    private float u;

    private float i;

    private float temperature;
}
