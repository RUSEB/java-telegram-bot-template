package ru.template.general.bot.handle;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.distributor.MessageDistributor;
import ru.template.general.bot.service.SendMessageService;

@Component
public class MessageHandle extends Handle{

    private final MessageDistributor messageDistributor;
    public MessageHandle(SendMessageService sendMessageService, MessageDistributor messageDistributor) {
        super(sendMessageService);
        this.messageDistributor = messageDistributor;
    }

    @Override
    public void handle(Update update){
        Answer answer = buildAnswer(messageDistributor,update);
        sendAnswer(answer);
    }
}
