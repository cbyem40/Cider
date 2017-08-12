package com.rachel.cider.vo;

import com.rachel.cider.vo.WeatheStation.WeatherElement;
import com.rachel.cider.vo.Weather;
import com.rachel.cider.vo.WeatherVO;

/**
 * Created by rachel.tung on 17/06/2017.
 */
public class StandardWeather implements Weather {

    String data;
    String time;

    @Override
    public void setTime(WeatherElement we) {

    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public void setData(WeatherElement we) {

    }

    @Override
    public String getData() {
        return null;
    }
}
