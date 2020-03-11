package com.accenture.tgbots.service.perfume.novelty;

import com.accenture.tgbots.dao.ProductDao;
import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.Product;
import com.accenture.tgbots.model.input.perfume.NoviceInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ByNoveltyHandler implements CommandHandler<NoviceInput> {

    private final ProductDao productDao = new ProductDao();

    @Override
    public ProcessingResult process(NoviceInput args) {
        List<String> res = productDao.getByNovelty(args)
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList());
        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Товаров за указанный период не найдено") : res);
    }
}
