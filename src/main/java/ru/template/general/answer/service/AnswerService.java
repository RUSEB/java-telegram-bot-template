package ru.template.general.answer.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.template.general.answer.type.*;

@Service
public class AnswerService{
    public Answer getMessage(Long telegramId,String message){
        return new MessageAnswer(telegramId,message);
    }

    public Answer getErrorAnswer(String error){
        return new ErrorAnswer(error);
    }
    public Answer getDefaultAnswer(Long telegramId){
        return new DefaultAnswer(telegramId);
    }

    public Answer getEmptyAnswer(Long telegramId){
        return new EmptyAnswer(telegramId);
    }
    public Answer getPhoto(Long telegramId, String text, InputFile photo){
        return new PhotoAnswer(telegramId,text,photo);
    }
}
