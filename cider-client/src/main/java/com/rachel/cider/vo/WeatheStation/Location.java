package com.rachel.cider.vo.WeatheStation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by rachel.tung on 17/06/2017.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
     private String locationName;
     private List<WeatherElement> weatherElement;
}
