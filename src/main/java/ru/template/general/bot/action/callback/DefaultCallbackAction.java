package ru.template.general.bot.action.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;

@Component
public class DefaultCallbackAction extends CallbackAction{
    @Override
    protected void setCommands() {

    }

    @Override
    protected Answer buildAnswer(Update update) {
        return defalutAnswer(update);
    }

    private Answer defalutAnswer(Update update){
        Long telegramId = telegramIdByUpdateService.get(update);
        return answerService.getDefaultAnswer(telegramId);
    }
}
