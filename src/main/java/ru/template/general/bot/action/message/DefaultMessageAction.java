package ru.template.general.bot.action.message;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;

@Component
public class DefaultMessageAction extends MessageAction {

    @Override
    protected void setCommands() {

    }

    @Override
    protected void setStates() {

    }

    @Override
    protected Answer buildAnswer(Update update) {
        return defaultAnswer(update);
    }


    private Answer defaultAnswer(Update update){
        Long telegramId = telegramIdByUpdateService.get(update);
        return answerService.getDefaultAnswer(telegramId);
    }
}