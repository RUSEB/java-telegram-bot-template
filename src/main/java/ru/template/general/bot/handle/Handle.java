package ru.template.general.bot.handle;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.distributor.Distributor;
import ru.template.general.bot.service.SendMessageService;

@Component
public abstract class Handle {

    private final SendMessageService sendMessageService;

    protected Handle(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    public abstract void handle(Update update) throws Exception;

    protected void sendAnswer(Answer answer){
        sendMessageService.send(answer);
    }

    protected Answer buildAnswer(Distributor distributor,Update update) {
        Answer answer = distributor.distribute(update);
        return answer;
    }
}
