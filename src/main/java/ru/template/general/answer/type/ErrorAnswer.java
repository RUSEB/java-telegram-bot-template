package ru.template.general.answer.type;

import lombok.extern.log4j.Log4j2;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

@Log4j2
public class ErrorAnswer extends Answer{
    private static final Long ADMIN_ID = 0L;

    public ErrorAnswer(String text) {
        super(text, ADMIN_ID);
    }

    @Override
    public void sendAnswer(TelegramBot telegramBot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(ADMIN_ID);
        sendMessage.enableMarkdown(markdownNeeded);
        if(hasInlineKeyboardMarkup()){
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        if(hasReplyKeyboardMarkup()){
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }else {
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        }
        logError(text);
        telegramBot.execute(sendMessage);
    }

    private void logError(String error){
        log.error(error);
    }
}
