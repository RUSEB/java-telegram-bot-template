package ru.template.general.bot.action.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CallbackActionFactory {
    private List<CallbackAction> callbackActions;

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    @Autowired
    public void setCallbackActions(List<CallbackAction> callbackActions) {
        this.callbackActions = callbackActions;
    }

    public CallbackAction getStrategy(String data) {

        boolean strategyFound = false;
        CallbackAction resultStrategy = defaultCallbackAction();

        for (CallbackAction callbackAction :callbackActions){
            if(!strategyFound&& callbackAction.hasData(data)){

                resultStrategy = callbackAction;
                strategyFound = true;

            } else if (strategyFound && callbackAction.hasData(data)) {

                String errorText = buildError(resultStrategy, callbackAction);
                return errorCallbackAction(errorText);
            }
        }
        return resultStrategy;
    }


    private String buildError(CallbackAction first, CallbackAction second){
        StringBuilder identicalDatas = new StringBuilder();
        identicalDatas.append("Error: Identical data found in ")
                .append(first.getClass()).append(" and ").append(second.getClass())
                .append(", data: ");
        first.getDataSet().stream()
                .filter(second.getDataSet()::contains)
                .map(String::valueOf)
                .forEach(s -> {
                    identicalDatas.append(s).append(", ");
                });
        return identicalDatas.delete(identicalDatas.length() - 2, identicalDatas.length()).append(".").toString();
    }
    private ErrorCallbackAction errorCallbackAction(String errorText){
        ErrorCallbackAction errorCallbackAction = applicationContext.getBean(ErrorCallbackAction.class);
        errorCallbackAction.setErrorText(errorText);
        return errorCallbackAction;
    }

    private DefaultCallbackAction defaultCallbackAction(){
        return applicationContext.getBean(DefaultCallbackAction.class);
    }


}
