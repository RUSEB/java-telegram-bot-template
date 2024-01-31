package ru.template.general.bot.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.TelegramBot;

@Service
@Log4j2
public class SendMessageService {
    @Value("${telegram.admin.id}")
    private String adminId;
    private final TelegramBot telegramBot;


    @Autowired
    public SendMessageService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void send(Answer answer) {
        trySend(answer);
    }

    private void trySend(Answer answer){
        try {
            answer.sendAnswer(telegramBot);
        } catch (TelegramApiException e) {
            log.error("An error occurred while sending a message to the user: "+ answer.getChatId()+". "+ "Error: "+e);
            sendErrorToAdmin(e);
        }
    }

    private void sendErrorToAdmin(Exception exception){
        trySendErrorToAdmin(exception);
    }

    private void trySendErrorToAdmin(Exception exception){
        try {
            SendMessage errorMessage = new SendMessage();
            errorMessage.setChatId(adminId);
            errorMessage.setText(exception.toString());
            telegramBot.execute(errorMessage);
        } catch (Exception ex) {
            log.error("An error with sending exception to admin occurred! " + "Error: " + exception);
        }
    }
}
