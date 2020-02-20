package com.accenture.tgbots.service;

import com.accenture.tgbots.dao.HandlerDbDao;
import com.accenture.tgbots.model.Billionair;
import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.model.input.HandlerInputDbTest;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
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
        return "Проверка взаимодействия с БД";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        HandlerInputDbTest input = (HandlerInputDbTest) args;

        List<Billionair> billionairsByCareer = handlerDbDao.findBillionairsByCareer(input.getCareer());

        List<String> res = billionairsByCareer
                .stream()
                .map(Billionair::toString)
                .collect(Collectors.toList());

        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Результатов не найдено") : res);
    }

    @Override
    public HandlerInput parseInputMessage(Message message) {
        HandlerInputDbTest model = new HandlerInputDbTest();
        String[] input = StringUtils.split(message.getText(), " ");
        if (input != null) {
            List<String> args = Arrays.asList(input).subList(1, input.length);
            if (args.size() >= 1) {
                model.setCareer(args.iterator().next());
            }
        }

        return model;
    }
}
