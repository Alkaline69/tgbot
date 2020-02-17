package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HandlerAbout implements CommandHandler {

    private List<String> descriptions = new ArrayList<>();

    public HandlerAbout() {
        descriptions.add(getDescription());
    }

    public HandlerAbout(List<CommandHandler> handlers) {
        descriptions.add(getDescription());
        for (CommandHandler nhd: handlers) {
            descriptions.add(nhd.getDescription() + "\n");
        }
    }

    @Override
    public boolean isSuitable(String text) {
        return "/about".equals(text);
    }

    @Override
    public String getDescription() {
        return "Справка по поддерживаемым командам: \n/about \t\tПолучить справку";
    }

    @Override
    public ProcessingResult process(List<String> args) {
        return new ProcessingResult(descriptions);
    }
}
