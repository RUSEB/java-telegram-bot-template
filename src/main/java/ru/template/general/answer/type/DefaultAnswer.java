package ru.template.general.answer.type;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

public class DefaultAnswer extends Answer{
    public DefaultAnswer(Long chatId) {
        super("Неизвестная команда.", chatId);
    }

    @Override
    public void sendAnswer(TelegramBot telegramBot) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        telegramBot.execute(sendMessage);
    }
}