package com.pvsoul.eec.yuandong.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InverterInfo {

    private  String id;

    private String inverterNo;

    private int status;

    private Float u;

    private Float i;

    private Float p;

    private int combinerBoxCount;
}
