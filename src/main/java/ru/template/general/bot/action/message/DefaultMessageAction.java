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
    protected Answer buildAnswer(Update update) {
        return defaultAnswer(update);
    }


    private Answer defaultAnswer(Update update){
        Long chatId = update.getMessage().getChatId();
        return answerService.getDefaultAnswer(chatId);
    }
}