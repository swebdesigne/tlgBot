package ru.hunt4.pro.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.hunt4.pro.utils.MessageUtils;

import java.util.Objects;

@Component
@Log4j
public class UpdateController {
    private TelegramBot telegramBot;
    private MessageUtils messageUtils;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if (Objects.isNull(update)) {
            log.error("Update is null");
        } else {
            if (Objects.nonNull(update.getMessage())) {
                distributeMessagesByType(update);
            } else {
                log.error("Unsupported message type " + update);
            }
        }
    }

    private void distributeMessagesByType(Update update) {
        var message = update.getMessage();
        if (Objects.nonNull(message.getText())) {
            processTextMessage(update);
        } else if (message.getDocument() != null) {
            processDocMessage(update);
        } else if (message.getPhoto() != null) {
            processPhotoMessage(update);
        } else {
            setUnsupportedMessageTypeView(update);
        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        var sendMessage = MessageUtils.generateSendMessageWithText(update,
                "Неподдерживаемый тип сообщения");
        setView(sendMessage);
    }

    private void setView(SendMessage sendMessage) {
        telegramBot.sendAnswerMessage(sendMessage);
    }

    private void processPhotoMessage(Update update) {
    }

    private void processDocMessage(Update update) {
    }

    private void processTextMessage(Update update) {
    }
}
