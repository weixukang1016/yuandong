package com.pvsoul.eec.yuandong.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GetPvStringTemperatureRequestVo {

    private String inverterId;

    private String pvStringId;

    private Date startTime;

    private Date endTime;
}
