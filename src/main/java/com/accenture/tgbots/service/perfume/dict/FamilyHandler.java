package com.accenture.tgbots.service.perfume.dict;

import com.accenture.tgbots.dao.FamilyDao;
import com.accenture.tgbots.model.dto.dict.Family;
import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.NoArgsHandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.stream.Collectors;

public class FamilyHandler implements CommandHandler<NoArgsHandlerInput> {

    private final FamilyDao familyDao = new FamilyDao();

    @Override
    public String getPrefix() {
        return "/sets";
    }

    @Override
    public String getDescription() {
        return "Получить полный перечень семейств";
    }

    @Override
    public ProcessingResult process(NoArgsHandlerInput args) {
        return new ProcessingResult(familyDao.list()
                .stream()
                .map(Family::getName)
                .collect(Collectors.toList())
        );
    }
}
