package ru.template.general.bot.action.callback;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;

@Component
@Scope("prototype")
@Setter
public class ErrorCallbackAction extends CallbackAction{

    private String errorText;

    @Override
    protected void setCommands() {

    }

    @Override
    protected Answer buildAnswer(Update update) {
        return getErrorAnswer();
    }

    private Answer getErrorAnswer(){
        return answerService.getErrorAnswer(errorText);
    }
}
