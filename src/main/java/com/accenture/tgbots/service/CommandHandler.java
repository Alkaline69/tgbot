package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {

    String getPrefix();

    String getDescription();

    ProcessingResult process(HandlerInput args);

    default boolean isSuitable(String text) {
        return getPrefix().equals(text);
    }

    default HandlerInput parseInputMessage(Message message) {
        return null;
    }

}
