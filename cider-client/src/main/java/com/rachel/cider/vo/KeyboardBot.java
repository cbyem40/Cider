package com.rachel.cider.vo;

import com.rachel.cider.Enum.MessageType;
import com.sun.rowset.internal.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class KeyboardBot extends Bot {

    @Autowired
    BotFactory botFactory;

    public KeyboardBot(){
        this.setMessageType(MessageType.Text);
        this.setDisplayText("keyboard");
    }

    @Override
    void prepareMessage(Update receivedMessage) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(receivedMessage.getMessage().getChatId())
                .setText("Here is your keyboard");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();

        List<Bot> botList = botFactory.getBotList();
        for (Bot bot: botList){
            if (row.size() == 3){
                keyboard.add(row);
                row = new KeyboardRow();
            }
            row.add(bot.getDisplayText());
        }
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        this.setTextMessage(message);

    }

}
