package ru.template.general.answer.type;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

public class MessageAnswer extends Answer{
    public MessageAnswer(Long chatId,String text) {
        super(text, chatId);
    }

    @Override
    public void sendAnswer(TelegramBot telegramBot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(markdownNeeded);
        if(hasInlineKeyboardMarkup()){
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        telegramBot.execute(sendMessage);
    }
}
