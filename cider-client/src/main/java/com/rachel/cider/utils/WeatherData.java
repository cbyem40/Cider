package com.rachel.cider.utils;

import com.rachel.cider.vo.WeatherVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Observable;

/**
 * Created by rachel.tung on 15/06/2017.
 */
@Getter
@Setter
public class WeatherData extends Observable{
//    private List<Observer> observers ;
    private WeatherVO weatherVO;

    private void measurementChanged(){
        setChanged();
        notifyObservers();
    }

    public void setWeatherData(WeatherVO weatherVO){
        this.weatherVO = weatherVO;
        measurementChanged();
    }


}
