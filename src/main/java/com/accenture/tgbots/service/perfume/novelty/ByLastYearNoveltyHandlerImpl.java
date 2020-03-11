package com.accenture.tgbots.service.perfume.novelty;

import com.accenture.tgbots.model.input.perfume.NoviceInput;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ByLastYearNoveltyHandlerImpl extends ByNoveltyHandler {
    @Override
    public String getPrefix() {
        return "/get_novelty_last_year";
    }

    @Override
    public String getDescription() {
        return "Поиск товарных позиций за последний год";
    }

    @Override
    public NoviceInput parseInputMessage(Message message) {
        return new NoviceInput(NoviceInput.Depth.LAST_YEAR);
    }
}
