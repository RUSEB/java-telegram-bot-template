package ru.template.general.bot.action.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.service.TelegramIdByUpdateService;


import java.util.HashSet;
import java.util.Set;

public abstract class CallbackAction {
    protected AnswerService answerService;
    protected TelegramIdByUpdateService telegramIdByUpdateService;

    protected Set<String> dataSet = new HashSet<>();

    @Autowired
    public void setTelegramIdByUpdateService(TelegramIdByUpdateService telegramIdByUpdateService){
        this.telegramIdByUpdateService = telegramIdByUpdateService;
    }
    @Autowired
    public void setAnswerService(AnswerService answerService){
        this.answerService = answerService;
    }
    public CallbackAction(){
        setCommands();
    }



    public final Answer getAnswer(Update update){
        return buildAnswer(update);
    }
    public final boolean hasData(String data){
        for (String element : dataSet) {
            if (element.contains(data)) {
                return true;
            }
        }
        return false;
    }
    public final Set<String> getDataSet(){
        return dataSet;
    }

    protected abstract void setCommands();

    protected abstract Answer buildAnswer(Update update);
}
