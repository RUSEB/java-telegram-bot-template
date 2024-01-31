package ru.template.general.bot;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.bot.handle.CallbackHandle;
import ru.template.general.bot.handle.MessageHandle;

@Component
@Service
public class TelegramFacade {

    private final CallbackHandle callbackHandle;
    private final MessageHandle messageHandle;

    public TelegramFacade(CallbackHandle callbackHandle, MessageHandle messageHandle) {
        this.callbackHandle = callbackHandle;
        this.messageHandle = messageHandle;
    }

    public void handleUpdate(Update update) {
        definitionUpdate(update);
    }

    private void definitionUpdate(Update update) {
        if(update.hasCallbackQuery()){
            callbackHandle.handle(update);
        }
        if(update.hasMessage()){
            messageHandle.handle(update);
        }
    }
}
