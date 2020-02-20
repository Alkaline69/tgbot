package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.model.input.HandlerInputEcho;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collections;

@Component
public class HandlerEcho implements CommandHandler {

    @Override
    public String getPrefix() {
        return "/echo";
    }

    @Override
    public boolean isSuitable(String text) {
        return getPrefix().equals(text);
    }

    @Override
    public String getDescription() {
        return "Повторяет введенные аргументы";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        ProcessingResult result = new ProcessingResult();
        HandlerInputEcho inputEcho = (HandlerInputEcho) args;
        result.setResults(Collections.singletonList(inputEcho.getText()));
        result.setStatus(ProcessingResult.Status.OK);
        return result;
    }

    @Override
    public HandlerInput parseInputMessage(Message message) {
        HandlerInputEcho handlerInputEcho = new HandlerInputEcho();
        handlerInputEcho.setText(message.getText());
        return handlerInputEcho;
    }
}
