package com.rachel.cider.vo;

import com.rachel.cider.Enum.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class HideKeyboardBot extends Bot {

    public HideKeyboardBot(){
        this.setMessageType(MessageType.Text);
        this.setDisplayText("hide keyboard");
    }

    @Override
    void prepareMessage(Update receivedMessage) {
        SendMessage msg = new SendMessage()
                .setChatId(receivedMessage.getMessage().getChatId())
                .setText("Keyboard hidden");
        ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
        msg.setReplyMarkup(keyboardMarkup);
        this.setTextMessage(msg);
    }

}
