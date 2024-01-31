package ru.template.general.bot.action.message;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.answer.type.Answer;

public class NullMessageAction  extends MessageActionStrategy{

    private static NullMessageAction nullMessageAction = null;

    public static NullMessageAction get(){
        if(nullMessageAction==null){
            nullMessageAction = new NullMessageAction();
        }
        return nullMessageAction;
    }
    private NullMessageAction(){

    }
    @Override
    protected void setCommands() {

    }

    @Override
    protected Answer buildAnswer(Update update) {
        return null;
    }
}
