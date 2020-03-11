package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.ProductDao;
import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.Product;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.Collections;

/**
 * Обработчик команды выдачи случайного товара
 */
public class GetRandomHandler implements CommandHandler {

    private final ProductDao productDao = new ProductDao();

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

        Product randomProduct = productDao.getRandomProduct();

        return new ProcessingResult(Collections.singletonList(randomProduct == null
                ? "Результатов не найдено"
                : randomProduct.toString()));
    }
}
