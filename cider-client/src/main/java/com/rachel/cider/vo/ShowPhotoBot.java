package com.rachel.cider.vo;

import com.rachel.cider.Enum.MessageType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Comparator;
import java.util.List;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class ShowPhotoBot extends Bot {

    public ShowPhotoBot(){
        this.setMessageType(MessageType.Photo);
        this.setDisplayText("show me photo");
    }

    @Override
    void prepareMessage(Update receivedMessage) {

        //TODO: get a random photo from the database.
        String photoId = "";
        String photoTitle = "title";

        SendPhoto photoMsg = new SendPhoto()
                .setChatId(receivedMessage.getMessage().getChatId())
                .setPhoto(photoId)
                .setCaption(photoTitle);
        this.setPhotoMessage(photoMsg);
    }

}
