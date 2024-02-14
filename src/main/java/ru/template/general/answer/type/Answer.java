package ru.template.general.answer.type;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

@Getter
public abstract class Answer {

    protected boolean markdownNeeded = false;
    protected InlineKeyboardMarkup inlineKeyboardMarkup = null;

    protected ReplyKeyboardMarkup replyKeyboardMarkup = null;
    protected String text;
    protected Long chatId;

    public Answer(String text,Long chatId){
        this.text = text;
        this.chatId = chatId;
    }


    public boolean hasInlineKeyboardMarkup(){
        return inlineKeyboardMarkup != null;
    }

    public boolean hasReplyKeyboardMarkup(){
        return replyKeyboardMarkup!=null;
    }

    public final void setMarkdownNeeded(boolean markdownNeeded) {
        this.markdownNeeded = markdownNeeded;
    }

    public abstract void sendAnswer(TelegramBot telegramBot) throws TelegramApiException;
    public final void setInlineKeyboardMarkup(InlineKeyboardMarkup inlineKeyboardMarkup){
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

    public void setReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup) {
        this.replyKeyboardMarkup = replyKeyboardMarkup;
    }
}
