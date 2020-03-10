package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler<T extends HandlerInput> {

    String getPrefix();

    String getDescription();

    ProcessingResult process(T args);

    default boolean isSuitable(String text) {
        return getPrefix().equals(text);
    }

    default T parseInputMessage(Message message) {
        return null;
    }

}
