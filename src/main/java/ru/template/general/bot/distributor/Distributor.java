package ru.template.general.bot.distributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;

@Component
public abstract class Distributor {

    protected AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService){
        this.answerService = answerService;
    }

    abstract public Answer distribute(Update update);
}
