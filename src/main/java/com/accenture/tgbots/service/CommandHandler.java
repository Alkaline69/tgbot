package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;

import java.util.List;

public interface CommandHandler {

    String getPrefix();

    boolean isSuitable(String text);

    String getDescription();

    ProcessingResult process(List<String> args);

}
