package ru.template.general.answer.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.template.general.answer.type.*;

@Service
public class AnswerService{
    public Answer getMessage(Long chatId,String message){
        return new MessageAnswer(chatId,message);
    }

    public Answer getErrorAnswer(String error){
        return new ErrorAnswer(error);
    }
    public Answer getDefaultAnswer(Long chatId){
        return new DefaultAnswer(chatId);
    }
    public Answer getPhoto(Long chatId, String text, InputFile photo){
        return new PhotoAnswer(chatId,text,photo);
    }
}
