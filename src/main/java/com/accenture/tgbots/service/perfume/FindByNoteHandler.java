package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;

public class FindByNoteHandler implements CommandHandler {
    @Override
    public String getPrefix() {
        return "/find_by_notes";
    }

    @Override
    public String getDescription() {
        return "подобрать парфюм по нотам";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        return null;
    }
}
