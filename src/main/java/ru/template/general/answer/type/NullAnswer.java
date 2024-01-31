package ru.template.general.answer.type;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

public class NullAnswer extends Answer{
    public NullAnswer() {
        super("NULL", -1L);
    }

    @Override
    public void sendAnswer(TelegramBot telegramBot) throws TelegramApiException {

    }
}
