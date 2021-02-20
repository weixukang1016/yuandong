package com.pvsoul.eec.yuandong.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PvStringInfoDao {

    private  String id;

    private String stringNo;

    private int status;

    private boolean isStandard;

    private Float u;

    private Float i;

    private Float temperature;
}
