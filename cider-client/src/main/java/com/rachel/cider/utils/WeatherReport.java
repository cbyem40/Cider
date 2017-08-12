package com.rachel.cider.utils;

import com.rachel.cider.vo.WeatherVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by rachel.tung on 15/06/2017.
 */
@Getter
@Setter
public class WeatherReport implements Observer{

    private Observable observable;
    private WeatherVO weatherVO;

    public WeatherReport(Observable observable ){
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        WeatherData weatherData = (WeatherData) o;
        this.setWeatherVO(weatherData.getWeatherVO());
        System.out.println(generateDisplayString());
    }

    private String generateDisplayString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Temperture: ").append(weatherVO.getTemp() ).append("\n");
        sb.append("RealFeel: ").append(weatherVO.getRealFeel()).append("\n");
        sb.append("Rain Percentage: ").append(weatherVO.getRainPercentage()).append("\n");
        sb.append("Humidity: ").append(weatherVO.getHumidity()).append("\n");

        return sb.toString();
    }
}
