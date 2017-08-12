package com.rachel.cider.vo;

import com.rachel.cider.vo.WeatheStation.WeatherElement;

/**
 * Created by rachel.tung on 17/06/2017.
 */
public interface Weather {

    void setTime(WeatherElement we);
    String getTime();
    void setData(WeatherElement we);
    String getData();

}
