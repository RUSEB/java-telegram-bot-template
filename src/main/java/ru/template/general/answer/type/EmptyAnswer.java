package ru.template.general.answer.type;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

public class EmptyAnswer extends Answer{
    public EmptyAnswer(Long telegramId) {
        super("", telegramId);
    }

    @Override
    public void sendAnswer(TelegramBot telegramBot) throws TelegramApiException {

    }
}
