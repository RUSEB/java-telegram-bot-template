package ru.template.general.bot.service;


import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ReplyMessageService {
    private final Locale locale;
    private final MessageSource messageSource;

    private final static String FILE_NAME = "classpath:messages";
    private final static String LOCALE = "ru-RU";

    public ReplyMessageService() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(FILE_NAME);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");

        this.messageSource = resourceBundleMessageSource;
        this.locale = Locale.forLanguageTag(LOCALE);
    }

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args,locale);
    }
}
