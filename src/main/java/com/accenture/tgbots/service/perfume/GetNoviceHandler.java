package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;

/**
 * Показать новинки
 */
public class GetNoviceHandler implements CommandHandler {
    @Override
    public String getPrefix() {
        return "/novice";
    }

    @Override
    public String getDescription() {
        return "показать новинки";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        return null;
    }
}
