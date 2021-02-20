package com.pvsoul.eec.yuandong.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformerInfoDao {

    private  String id;

    private String transformerNo;

    private int status;

    private Float lU;

    private Float hU;

    private Float fac;

    private Float pac;

    private Float pFactor;

    private int inverterCount;
}
