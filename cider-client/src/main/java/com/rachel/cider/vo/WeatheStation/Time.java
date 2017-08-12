package com.rachel.cider.vo.WeatheStation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rachel.tung on 16/06/2017.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Time {
    private String startTime;
    private String endTime;
    private Integer elementValue;
    private String dataTime;

}