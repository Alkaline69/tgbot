package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;

public class SplitToNoteHandler implements CommandHandler {
    @Override
    public String getPrefix() {
        return "/to_notes";
    }

    @Override
    public String getDescription() {
        return "разложить введённый парфюм на ноты";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        return null;
    }
}
