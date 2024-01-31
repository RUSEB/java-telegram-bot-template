package ru.template.general.bot.distributor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.action.message.MessageAction;
import ru.template.general.bot.action.message.MessageActionFactory;

@Component
@Log4j2
public class MessageDistributor extends Distributor{
    private final MessageActionFactory messageActionFactory;

    public MessageDistributor(AnswerService answerService, MessageActionFactory messageActionFactory) {
        super(answerService);
        this.messageActionFactory = messageActionFactory;
    }
    @Override
    public Answer distribute(Update update) {
        String command = update.getMessage().getText();
        MessageAction action = messageActionFactory.getStrategy(command);
        return action.getAnswer(update);
    }

}
