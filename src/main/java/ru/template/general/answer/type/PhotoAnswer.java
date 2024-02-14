package ru.template.general.answer.type;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.template.general.bot.TelegramBot;

@Getter
public class PhotoAnswer extends Answer{

    private InputFile image;
    public PhotoAnswer(Long chatId,String text, InputFile image) {
        super(text, chatId);
        this.image = image;
    }

    @Override
    public void sendAnswer(TelegramBot telegramBot) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(image);
        sendPhoto.setChatId(chatId);
        if(markdownNeeded){
            sendPhoto.setParseMode(ParseMode.MARKDOWN);
        }
        if(hasInlineKeyboardMarkup()){
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        }else {
            sendPhoto.setReplyMarkup(new ReplyKeyboardRemove(true));
        }
        if(hasReplyKeyboardMarkup()){
            sendPhoto.setReplyMarkup(replyKeyboardMarkup);
        }
        telegramBot.execute(sendPhoto);
    }
}
