package com.accenture.tgbots.service;

import com.accenture.tgbots.dao.HandlerDbDao;
import com.accenture.tgbots.model.Billionair;
import com.accenture.tgbots.model.ProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HandlerDbTest implements CommandHandler {

    //todo: fix spring DI
    private HandlerDbDao handlerDbDao = new HandlerDbDao();

    @Override
    public String getPrefix() {
        return "/db";
    }

    @Override
    public boolean isSuitable(String text) {
        return getPrefix().equals(text);
    }

    @Override
    public String getDescription() {
        return "/db [фильтр] Проверка взаимодействия с БД";
    }

    @Override
    public ProcessingResult process(List<String> args) {
        List<Billionair> billionairsByCareer = handlerDbDao.findBillionairsByCareer(args.iterator().next());

        List<String> res = billionairsByCareer
                .stream()
                .map(Billionair::toString)
                .collect(Collectors.toList());

        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Результатов не найдено") : res);
    }
}
