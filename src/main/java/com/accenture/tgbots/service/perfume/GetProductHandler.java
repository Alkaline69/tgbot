package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.HandlerProductDao;
import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.Product;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обработчик команды выдачи случайного товара
 */
public class GetProductHandler implements CommandHandler {

    private final HandlerProductDao productDao = new HandlerProductDao();

    @Override
    public String getPrefix() {
        return "/product";
    }

    @Override
    public String getDescription() {
        return "выдать 20 единиц товара парфюмерии";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {

        List<String> res = productDao.getAllProducts()
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList());

        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Результатов не найдено") : res);
    }
}
