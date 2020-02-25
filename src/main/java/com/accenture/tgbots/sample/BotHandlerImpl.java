package com.accenture.tgbots.sample;

import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;
import com.accenture.tgbots.service.HandlerAbout;
import com.accenture.tgbots.service.HandlerEcho;
import com.accenture.tgbots.service.perfume.*;
import com.annimon.tgbotsmodule.BotHandler;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class BotHandlerImpl extends BotHandler {

    private final BotConfig botConfig;

    private List<CommandHandler> handlers;
    private Map<String, CommandHandler> commandMap;

    public BotHandlerImpl(BotConfig botConfig) {
        this.botConfig = botConfig;

        createHandlers();
        fillCommandMap();
    }

    @Override
    protected BotApiMethod onUpdate(@NotNull Update update) {
        if (!update.hasMessage()) {
            return null;
        }

        Message message = update.getMessage();
        if (!message.hasText()) {
            return null;
        }

        String text = message.getText();
        long chatId = message.getChatId();

        SendMessage sm = new SendMessage(chatId, processInput(message));
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            //BotLogger.error("SEND", e.toString());
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    private String processInput(Message message) {
        String[] input = StringUtils.split(message.getText(), " ");
        if (input != null) {
            CommandHandler handler = getHandler(input[0]);

            ProcessingResult processingResult;
            try {
                HandlerInput handlerInput = handler.parseInputMessage(message);
                processingResult = handler.process(handlerInput);
            } catch (RuntimeException e) {
                processingResult = new ProcessingResult();
                processingResult.setStatus(ProcessingResult.Status.INTERNAL_ERROR);
                processingResult.setResults(Collections.singletonList(e.getLocalizedMessage()));
            }
            if (processingResult.isOk()) {
                return StringUtils.join(processingResult.getResults(), "\n");
            } else {
                return "Ошибка обработки: " + processingResult.getResults();
            }
        }

        return "Неверный формат запроса";
    }

    private CommandHandler getHandler(String cmd) {
        CommandHandler hnd = commandMap.get(cmd);
        return hnd != null ? hnd : commandMap.get("/help");
    }

    private void createHandlers() {
        handlers = List.of(
                new HandlerEcho(),
                new FindByNoteHandler(), new GetNoviceHandler(), new GetRandomHandler(), new SplitToNoteHandler(), new GetProductHandler(), new ByFamilyHandler()
        );

        //todo: fill by Handler beans
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext();
//        Map<String, CommandHandler> beansList = context.getBeansOfType(CommandHandler.class);
//
//        for (String key : beansList.keySet()) {
//            System.out.println(key + " = " + beansList.get(key));
//        }

    }

    private void fillCommandMap() {
        commandMap = new HashMap<>();
        for (CommandHandler hnd : handlers) {
            commandMap.put(hnd.getPrefix(), hnd);
        }

        commandMap.put("/help", new HandlerAbout(handlers));
    }

}
