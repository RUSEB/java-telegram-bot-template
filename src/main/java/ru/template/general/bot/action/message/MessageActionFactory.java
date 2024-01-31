package ru.template.general.bot.action.message;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Log4j2
public class MessageActionFactory {
    List<MessageAction> messageActions;

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Autowired
    public void setMessageActionStrategies(List<MessageAction> messageActionStrategies){
        this.messageActions = messageActionStrategies;
    }
    public MessageAction getStrategy(String command) {
        boolean strategyFound = false;
        MessageAction resultStrategy = defaultMessageAction();
        for (MessageAction messageAction:messageActions){
            if(!strategyFound&&messageAction.hasCommand(command)){
                resultStrategy = messageAction;
                strategyFound = true;
            } else if (strategyFound&&messageAction.hasCommand(command)) {
                String errorText = buildError(resultStrategy,messageAction);
                return errorMessageAction(errorText);
            }
        }
        return resultStrategy;
    }


    private String buildError(MessageAction first,MessageAction second){
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

    private ErrorMessageAction errorMessageAction(String errorText){
        ErrorMessageAction errorMessageAction = applicationContext.getBean(ErrorMessageAction.class);
        errorMessageAction.setErrorText(errorText);
        return errorMessageAction;
    }

    private DefaultMessageAction defaultMessageAction(){
        return applicationContext.getBean(DefaultMessageAction.class);
    }
}
