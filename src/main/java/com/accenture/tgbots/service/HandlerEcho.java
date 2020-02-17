package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class HandlerEcho implements CommandHandler {
    @Override
    public boolean isSuitable(String text) {
        return "/echo".equals(text);
    }

    @Override
    public String getDescription() {
        return "/echo \tПовторяет введенные аргументы";
    }

    @Override
    public ProcessingResult process(List<String> args) {
        ProcessingResult result = new ProcessingResult();
        result.setResults(args);
        result.setStatus(ProcessingResult.Status.OK);
        return result;
    }
}
