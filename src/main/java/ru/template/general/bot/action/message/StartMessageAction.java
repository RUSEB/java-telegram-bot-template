package ru.template.general.bot.action.message;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;

@Component
public class StartMessageAction extends MessageActionStrategy{

    @Override
    protected void setCommands() {
        commands.add("/start");
        commands.add("/end");
    }

    @Override
    protected Answer buildAnswer(Update update) {
        return answerService.getMessage(update.getMessage().getChatId(),"Была введена команда /start");
    }


}
