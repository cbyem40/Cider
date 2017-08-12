package com.rachel.cider.vo;

import com.rachel.cider.Enum.MessageType;
import com.rachel.cider.service.SportsCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class DefaultBot extends Bot {

    public DefaultBot(){
        this.setMessageType(MessageType.Text);
        this.setDisplayText("lol");
    }

    @Override
    void prepareMessage(Update receivedMessage) {
        SendMessage message = new SendMessage()
                .setChatId(receivedMessage.getMessage().getChatId())
                .setText("lol");
        this.setTextMessage(message);
    }

}
