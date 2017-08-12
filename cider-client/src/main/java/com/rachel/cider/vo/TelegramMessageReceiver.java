package com.rachel.cider.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class TelegramMessageReceiver extends TelegramLongPollingBot  {

    @Autowired
    BotFactory botFactory;

    @Value("${application.connect.telegram.bot.username}")
    String telegramBotUsername;

    @Value("${application.connect.telegram.bot.token}")
    String telegramBotToken;


    @Override
    public void onUpdateReceived(Update update) {
        Bot bot = botFactory.createBot(update);
        bot.prepareMessage(update);
        bot.sendMessage(this);
    }

    @Override
    public String getBotUsername() {
        return telegramBotUsername;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }
}
