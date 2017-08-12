package com.rachel.cider.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * Created by rachel.tung on 15/06/2017.
 */
@Getter
@Setter
public class WeatherVO{

    private Integer temp;
    private Integer rainPercentage;
    private Integer realFeel;
    private Integer humidity;
    private LocalDateTime time;

    public String getDisplayInformation(){
        return "*Date:* " + this.time.toString() + "\n"
                + "Temperature: " + this.getTemp() + "℃\n"
                + "RealFeel: " + this.getRealFeel() + "℃\n"
                + "Percentage to Rain: " + this.getRainPercentage() + "%\n"
                + "Humidity: " + this.getHumidity() + "\n";
    }

}
