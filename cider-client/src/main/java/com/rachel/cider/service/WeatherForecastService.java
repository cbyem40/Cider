package com.rachel.cider.service;

import com.rachel.cider.Enum.District;
import com.rachel.cider.vo.WeatherVO;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface WeatherForecastService {
    List<WeatherVO> getWeatherForecast(District district) throws UnsupportedEncodingException;
}
