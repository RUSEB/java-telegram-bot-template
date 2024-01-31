package ru.template.general.bot.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.template.general.bot.TelegramBot;


@Log4j2
@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.bot.userName}")
    private String botUserName;
    @Value("${telegram.bot.webHookPath}")
    private String botPath;

    @Bean
    public TelegramBot accountingBot(){
        TelegramBot bot = new TelegramBot();
        bot.setBotToken(botToken);
        bot.setBotUserName(botUserName);
        bot.setBotPath(botPath);
        log(bot);
        return bot;
    }
    private void log(TelegramBot bot){
        log.info(String.format("Telegram bot start with token - %s, userName - %s, webhook path - %s",bot.getBotToken(),bot.getBotUsername(),bot.getBotPath()));
    }
}
