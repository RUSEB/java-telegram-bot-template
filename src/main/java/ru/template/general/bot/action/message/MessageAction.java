package ru.template.general.bot.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.database.model.State;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;
import ru.template.general.bot.service.ReplyMessageService;
import ru.template.general.bot.service.TelegramIdByUpdateService;

import java.util.HashSet;
import java.util.Set;

@Component
public abstract class MessageAction {

    protected AnswerService answerService;

    protected Set<String> commands = new HashSet<>();

    protected Set<State> states = new HashSet<>();
    protected TelegramIdByUpdateService telegramIdByUpdateService;


    protected ReplyMessageService replyMessageService;


    @Autowired
    public void setAnswerService(AnswerService answerService){
        this.answerService = answerService;
    }
    @Autowired
    public void setReplyMessageService(ReplyMessageService replyMessageService){
        this.replyMessageService = replyMessageService;
    }

    @Autowired
    public void setTelegramByUpdateService(TelegramIdByUpdateService telegramIdByUpdateService){
        this.telegramIdByUpdateService = telegramIdByUpdateService;
    }
    public MessageAction(){
        setCommands();
        setStates();
    }

    protected abstract void setCommands();
    protected abstract void setStates();

    public final Answer getAnswer(Update update){
        return buildAnswer(update);
    }
    public final boolean hasCommand(String command){
        return commands.contains(command);
    }
    public final boolean hasState(State state){
        return states.contains(state);
    }
    public final boolean hasStates(){
        return states.size()>0;
    }
    public final Set<String> getCommands(){
        return commands;
    }
    public final Set<State> getStates(){return states;}
    protected abstract Answer buildAnswer(Update update);
}