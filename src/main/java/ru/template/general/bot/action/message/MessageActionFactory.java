package ru.template.general.bot.action.message;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Log4j2
public class MessageActionFactory {
    List<MessageActionStrategy> messageActionStrategies;

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setMessageActionStrategies(List<MessageActionStrategy> messageActionStrategies){
        this.messageActionStrategies = messageActionStrategies;
    }
    public MessageActionStrategy getStrategy(String command) {
        boolean strategyFound = false;
        MessageActionStrategy resultStrategy = NullMessageAction.get();
        for (MessageActionStrategy messageActionStrategy:messageActionStrategies){
            if(!strategyFound&&messageActionStrategy.hasCommand(command)){
                resultStrategy = messageActionStrategy;
                strategyFound = true;
            } else if (strategyFound&&messageActionStrategy.hasCommand(command)) {
                String errorText = buildError(resultStrategy,messageActionStrategy);
                ErrorMessageAction errorMessageAction = applicationContext.getBean(ErrorMessageAction.class);
                errorMessageAction.setErrorText(errorText);
                return errorMessageAction;
            }
        }
        return resultStrategy;
    }


    private String buildError(MessageActionStrategy first,MessageActionStrategy second){
        StringBuilder identicalCommands = new StringBuilder();
        identicalCommands.append("Error: Identical commands found in ")
                         .append(first.getClass()).append(" and ").append(second.getClass())
                         .append(", commands: ");
        first.getCommands().stream()
                .filter(second.getCommands()::contains)
                .map(String::valueOf)
                .forEach(s -> {
                    identicalCommands.append(s).append(", ");
                });
        return identicalCommands.delete(identicalCommands.length() - 2, identicalCommands.length()).append(".").toString();
    }
}
