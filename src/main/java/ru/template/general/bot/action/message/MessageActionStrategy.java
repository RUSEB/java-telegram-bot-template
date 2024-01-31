package ru.template.general.bot.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.service.AnswerService;
import ru.template.general.answer.type.Answer;

import java.util.HashSet;
import java.util.Set;

@Component
public abstract class MessageActionStrategy {

    protected AnswerService answerService;
    protected Set<String> commands = new HashSet<>();

    public MessageActionStrategy(){
        setCommands();
    }

    @Autowired
    public void setAnswerService(AnswerService answerService){
        this.answerService = answerService;
    }

    protected abstract void setCommands();

    public final Answer getAnswer(Update update){
        return buildAnswer(update);
    }
    public final boolean hasCommand(String command){
        return commands.contains(command);
    }
    public final Set<String> getCommands(){
        return commands;
    }
    protected abstract Answer buildAnswer(Update update);
}
