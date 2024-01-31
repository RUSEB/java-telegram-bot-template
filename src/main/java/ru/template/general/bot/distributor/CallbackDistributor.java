package ru.template.general.bot.distributor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;

@Component
public class CallbackDistributor extends Distributor{
    public CallbackDistributor(AnswerService answerService) {
        super(answerService);
    }

    @Override
    public Answer distribute(Update update) {
        return null;
    }
}
