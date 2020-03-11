package com.accenture.tgbots.service.perfume.novelty;

import com.accenture.tgbots.model.input.perfume.NoviceInput;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ByHalfYearNoveltyHandlerImpl extends ByNoveltyHandler {
    @Override
    public String getPrefix() {
        return "/get_novelty_six_month";
    }

    @Override
    public String getDescription() {
        return "Поиск товарных позиций за последние полгода";
    }

    @Override
    public NoviceInput parseInputMessage(Message message) {
        return new NoviceInput(NoviceInput.Depth.SIX_MONTH);
    }
}
