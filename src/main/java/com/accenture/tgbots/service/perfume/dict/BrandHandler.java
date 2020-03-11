package com.accenture.tgbots.service.perfume.dict;

import com.accenture.tgbots.dao.BrandDao;
import com.accenture.tgbots.model.Brand;
import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.NoArgsHandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.stream.Collectors;

public class BrandHandler implements CommandHandler<NoArgsHandlerInput> {

    private final BrandDao brandDao = new BrandDao();

    @Override
    public String getPrefix() {
        return "/brands";
    }

    @Override
    public String getDescription() {
        return "Получить полный перечень доступных брендов";
    }

    @Override
    public ProcessingResult process(NoArgsHandlerInput args) {
        return new ProcessingResult(brandDao.list()
                .stream()
                .map(Brand::getName)
                .collect(Collectors.toList())
        );
    }
}
