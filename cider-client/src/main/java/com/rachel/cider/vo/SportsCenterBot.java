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
public class SportsCenterBot extends Bot {

    @Autowired
    SportsCenterService sportsCenterService;

    public SportsCenterBot(){
        this.setMessageType(MessageType.Text);
        this.setDisplayText("sports center");
    }

    @Override
    void prepareMessage(Update receivedMessage) {
        List<RealTimeVisitor> realTimeVisitors = sportsCenterService.getRealTimeVisitor();
        StringJoiner sj = new StringJoiner("\n");
        realTimeVisitors.forEach(sc -> sj.add(sc.toString()));
        SendMessage message = new SendMessage()
                .setChatId(receivedMessage.getMessage().getChatId())
                .setParseMode("Markdown").setText(sj.toString());
        this.setTextMessage(message);
    }

}
