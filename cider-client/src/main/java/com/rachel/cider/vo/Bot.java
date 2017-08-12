package com.rachel.cider.vo;


import com.rachel.cider.Enum.MessageType;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Getter
@Setter
public abstract class Bot {

    String displayText;
    MessageType messageType;
    SendMessage textMessage ;
    SendPhoto photoMessage;

    abstract void prepareMessage(Update receivedMessage);

    void sendMessage(TelegramLongPollingBot pollingBot){
        try{
            if (this.messageType.equals(MessageType.Text)){
                pollingBot.sendMessage(this.textMessage);
            }else{
                pollingBot.sendPhoto(this.photoMessage);
            }
       } catch (TelegramApiException e) {
           e.printStackTrace();
       }
    }
}
