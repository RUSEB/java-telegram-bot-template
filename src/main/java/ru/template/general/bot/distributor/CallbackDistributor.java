package ru.template.general.bot.distributor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.action.callback.CallbackAction;
import ru.template.general.bot.action.callback.CallbackActionFactory;

@Component
public class CallbackDistributor extends Distributor{

    private CallbackActionFactory callbackActionFactory;


    @Autowired
    public void setCallbackActionFactory(CallbackActionFactory callbackActionFactory){
        this.callbackActionFactory = callbackActionFactory;
    }

    @Override
    public Answer distribute(Update update) {
        String data = update.getCallbackQuery().getData();
        CallbackAction callbackAction = callbackActionFactory.getStrategy(data);
        return callbackAction.getAnswer(update);
    }
}
