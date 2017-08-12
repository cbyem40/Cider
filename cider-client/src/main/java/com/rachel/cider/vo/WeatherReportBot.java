package com.rachel.cider.vo;

import com.rachel.cider.Enum.District;
import com.rachel.cider.Enum.MessageType;
import com.rachel.cider.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class WeatherReportBot extends Bot {

    @Autowired
    WeatherForecastService weatherForecastService;

    public WeatherReportBot(){
        this.setMessageType(MessageType.Text);
        this.setDisplayText("weather report");
    }

    @Override
    void prepareMessage(Update receivedMessage) {
        List<WeatherVO> weatherForecast  =  new ArrayList<>();
        try {
            weatherForecast = weatherForecastService.getWeatherForecast(District.內湖區);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringJoiner sj = new StringJoiner("\n");
        weatherForecast.forEach(weather -> sj.add(weather.getDisplayInformation()));
        SendMessage message = new SendMessage()
                .setChatId(receivedMessage.getMessage().getChatId())
                .setParseMode("Markdown").setText(sj.toString());
        this.setTextMessage(message);
    }

}
