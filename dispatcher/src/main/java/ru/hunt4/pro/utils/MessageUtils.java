package ru.hunt4.pro.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageUtils {
    public static SendMessage generateSendMessageWithText(Update update, String text) {
        var message = update.getMessage();
        var senMessage = new SendMessage();
        senMessage.setChatId(message.getChatId().toString());
        senMessage.setText(text);
        return senMessage;
    }
}
