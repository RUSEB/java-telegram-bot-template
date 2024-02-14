package ru.template.general.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TelegramIdByUpdateService {
    public Long get(Update update){
        if(update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }else{
            return update.getCallbackQuery().getFrom().getId();
        }
    }
}