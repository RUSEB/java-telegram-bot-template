package ru.template.general.bot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Setter
@Getter
public class TelegramBot extends TelegramWebhookBot {


    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.userName}")
    private String botUserName;
    @Value("${telegram.bot.webHookPath}")
    private String botPath;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update){
        return null;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }
    @Override
    public String getBotUsername() {
        return botUserName;
    }
    @Override
    public String getBotToken() {
        return botToken;
    }
}



