package com.accenture.tgbots.service.perfume.dict;

import com.accenture.tgbots.dao.TitleDao;
import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.Title;
import com.accenture.tgbots.model.input.NoArgsHandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.stream.Collectors;

public class TitleHandler implements CommandHandler<NoArgsHandlerInput> {

    private final TitleDao titleDao = new TitleDao();

    @Override
    public String getPrefix() {
        return "/titles";
    }

    @Override
    public String getDescription() {
        return "Получить перечень доступных наименований парфюма";
    }

    @Override
    public ProcessingResult process(NoArgsHandlerInput args) {
        return new ProcessingResult(titleDao.list()
                .stream()
                .map(Title::getName)
                .collect(Collectors.toList())
        );
    }
}
