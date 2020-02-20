package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;

/**
 * Обработчик команды выдачи случайного товара
 */
public class GetRandomHandler implements CommandHandler {

    @Override
    public String getPrefix() {
        return "/random";
    }

    @Override
    public String getDescription() {
        return "выдать случайный парфюм";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        return null;
    }
}
