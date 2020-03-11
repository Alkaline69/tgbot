package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.ProductDao;
import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.Product;
import com.accenture.tgbots.model.input.perfume.ByFamilyInput;
import com.accenture.tgbots.service.CommandHandler;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ByFamilyHandler implements CommandHandler<ByFamilyInput> {

    private final ProductDao productDao = new ProductDao();

    @Override
    public String getPrefix() {
        return "/get_by_set";
    }

    @Override
    public String getDescription() {
        return "подобрать парфюм по семействам";
    }

    @Override
    public ProcessingResult process(ByFamilyInput input) {
        List<String> res = productDao.getByFamily(input.getFamily())
                .stream()
                .map(Product::toString)
                .collect(Collectors.toList());

        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Результатов не найдено") : res);
    }

    @Override
    public ByFamilyInput parseInputMessage(Message message) {
        ByFamilyInput model = new ByFamilyInput();
        String[] input = StringUtils.split(message.getText(), " ");
        if (input != null) {
            List<String> args = Arrays.asList(input).subList(1, input.length);
            if (args.size() >= 1) {
                model.setFamily(args.iterator().next());
            } else {
                throw new RuntimeException("Введите только одно семейство");
            }
        }

        return model;
    }
}
