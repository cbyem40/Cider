package com.rachel.cider.vo.WeatheStation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rachel.cider.vo.ElementName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by rachel.tung on 16/06/2017.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStationsResponse {

    private Boolean success;
    private Object result;
    private Record records;

}
