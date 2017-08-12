package com.rachel.cider.vo;

import com.rachel.cider.Enum.MessageType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Comparator;
import java.util.List;

/**
 * Created by rachel.tung on 09/07/2017.
 */
@Component
public class ParsePhotoBot extends Bot {

    public ParsePhotoBot(){
        this.setMessageType(MessageType.Text);
    }

    @Override
    void prepareMessage(Update receivedMessage) {

        List<PhotoSize> photos = receivedMessage.getMessage().getPhoto();
        // Know file_id
        String f_id = photos.stream()
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null).getFileId();
        // Know photo width
        int f_width = photos.stream()
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null).getWidth();
        // Know photo height
        int f_height = photos.stream()
                .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                .findFirst()
                .orElse(null).getHeight();

        // TODO: save into database.

        // Set photo caption
        String caption = "Uploaded file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
        SendMessage message = new SendMessage();
        message.setChatId(receivedMessage.getMessage().getChatId()).setText(caption);
        this.setTextMessage(message);
    }

}
