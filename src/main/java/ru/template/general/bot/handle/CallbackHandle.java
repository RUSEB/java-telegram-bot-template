package ru.template.general.bot.handle;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.distributor.CallbackDistributor;
import ru.template.general.bot.service.SendMessageService;

@Component
public class CallbackHandle extends Handle{

    private final CallbackDistributor callbackDistributor;
    public CallbackHandle(SendMessageService sendMessageService, CallbackDistributor callbackDistributor) {
        super(sendMessageService);
        this.callbackDistributor = callbackDistributor;
    }

    @Override
    public void handle(Update update) {
        Answer answer = buildAnswer(callbackDistributor,update);
        sendAnswer(answer);
    }

}
