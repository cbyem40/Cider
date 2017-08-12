package com.rachel.cider.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class BotFactory {

    @Autowired
    private SportsCenterBot sportsCenterBot;

    @Autowired
    private WeatherReportBot weatherReportBot;

    @Autowired
    private KeyboardBot keyboardBot;

    @Autowired
    private HideKeyboardBot hideKeyboardBot;

    @Autowired
    private DefaultBot defaultBot;

    @Autowired
    private ParsePhotoBot parsePhotoBot;

    @Autowired
    private ShowPhotoBot showPhotoBot;

    private List<Bot> botList ;

    public BotFactory(){
    }

    @PostConstruct
    public void initBotList(){
        botList = new ArrayList<>();
        botList.add(sportsCenterBot);
        botList.add(weatherReportBot);
        botList.add(keyboardBot);
        botList.add(hideKeyboardBot);
        botList.add(showPhotoBot);
        botList.add(defaultBot);
    }

    public Bot createBot(Update receivedMessage){
        Bot matchBot  = botList.stream().filter(bot -> bot.getDisplayText().equals(receivedMessage.getMessage().getText())).findFirst().orElse(null);
        if (matchBot  != null){
            return matchBot;
        }else{
            if (receivedMessage.getMessage().hasPhoto()){
                return parsePhotoBot;
            }
            return defaultBot;
        }
    }

    List<Bot> getBotList(){
        return this.botList;
    }

}
