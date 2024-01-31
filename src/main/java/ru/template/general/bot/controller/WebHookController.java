package ru.template.general.bot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.template.general.bot.TelegramFacade;

@RestController
@Log4j2
public class WebHookController {
    private final TelegramFacade telegramFacade;

    public WebHookController(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }

    @PostMapping("/")
    public void onUpdateReceived(@RequestBody Update update){
        if(update!=null){
            telegramFacade.handleUpdate(update);
        }else{
            log.warn("Update has been received empty: NULL");
        }
    }
}
