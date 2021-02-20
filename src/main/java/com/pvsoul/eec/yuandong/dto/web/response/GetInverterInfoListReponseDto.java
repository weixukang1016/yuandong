package com.pvsoul.eec.yuandong.dto.web.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetInverterInfoListReponseDto implements Serializable {

    private static final long serialVersionUID = 7960912139203736949L;

    private int pageNum;

    private int pageSize;

    private int pageCount;

    private long totalCount;

    private List<InverterInfoDto> inverterInfos;
}
