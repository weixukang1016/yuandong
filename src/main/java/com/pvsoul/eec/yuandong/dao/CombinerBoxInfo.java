package com.pvsoul.eec.yuandong.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CombinerBoxInfo {

    private  String id;

    private String boxNo;

    private int status;

    private Float u;

    private Float i;

    private Float p;

    private Float temperature;

    private int pvStringCount;
}
