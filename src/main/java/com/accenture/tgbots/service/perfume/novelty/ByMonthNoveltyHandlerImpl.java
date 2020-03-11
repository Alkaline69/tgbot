package com.accenture.tgbots.service.perfume.novelty;

import com.accenture.tgbots.model.input.perfume.NoviceInput;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ByMonthNoveltyHandlerImpl extends ByNoveltyHandler {
    @Override
    public String getPrefix() {
        return "/get_novelty_month";
    }

    @Override
    public String getDescription() {
        return "ПОиск товарных позиций за последний месяц";
    }

    @Override
    public NoviceInput parseInputMessage(Message message) {
        return new NoviceInput(NoviceInput.Depth.ONE_MONTH);
    }
}
