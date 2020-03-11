package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.ProductDao;
import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.Product;
import com.accenture.tgbots.model.input.NoArgsHandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ByDiscountHandler implements CommandHandler<NoArgsHandlerInput> {

    private final ProductDao productDao = new ProductDao();

    @Override
    public String getPrefix() {
        return "/get_by_discount";
    }

    @Override
    public String getDescription() {
        return "Товары со скидками";
    }

    @Override
    public ProcessingResult process(NoArgsHandlerInput args) {
        List<String> res = productDao.getAllProducts()
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList());
        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Товаров со скидками не найдено") : res);
    }
}
