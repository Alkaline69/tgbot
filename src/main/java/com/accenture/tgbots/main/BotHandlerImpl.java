package com.accenture.tgbots.main;

import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.service.CommandHandler;
import com.accenture.tgbots.service.HandlerAbout;
import com.accenture.tgbots.service.perfume.*;
import com.accenture.tgbots.service.perfume.dict.BrandHandler;
import com.accenture.tgbots.service.perfume.dict.FamilyHandler;
import com.accenture.tgbots.service.perfume.dict.NoteHandler;
import com.accenture.tgbots.service.perfume.dict.TitleHandler;
import com.accenture.tgbots.service.perfume.novelty.ByHalfYearNoveltyHandlerImpl;
import com.accenture.tgbots.service.perfume.novelty.ByLastYearNoveltyHandlerImpl;
import com.accenture.tgbots.service.perfume.novelty.ByMonthNoveltyHandlerImpl;
import com.accenture.tgbots.service.perfume.novelty.BySeasonNoveltyHandlerImpl;
import com.annimon.tgbotsmodule.BotHandler;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация бота для парфюмерного магазина
 */
public class BotHandlerImpl extends BotHandler {

    private final BotConfig botConfig;

    private List<CommandHandler> handlers;
    private Map<String, CommandHandler> commandMap;

    public BotHandlerImpl(BotConfig botConfig) {
        this.botConfig = botConfig;

        createHandlers();
        fillCommandMap();
    }

    /**
     * Обработчик входящих сообщений боту
     * @param update входные данные
     * @return
     */
    @Override
    protected BotApiMethod onUpdate(@NotNull Update update) {
        if (!update.hasMessage()) {
            return null;
        }
        Message message = update.getMessage();
        if (!message.hasText()) {
            return null;
        }

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

    /**
     * Создание отдельных обработчиков-команд бота
     */
    private void createHandlers() {
        handlers = List.of(
                new NoteHandler(), new BrandHandler(), new TitleHandler(), new FamilyHandler(),
                new FindByNoteHandler(), new GetNoviceHandler(), new GetRandomHandler(), new SplitToNoteHandler(), new GetProductHandler(), new ByFamilyHandler(),
                new ByDiscountHandler(),
                new ByMonthNoveltyHandlerImpl(), new BySeasonNoveltyHandlerImpl(), new ByHalfYearNoveltyHandlerImpl(), new ByLastYearNoveltyHandlerImpl(),
                new SplitToNoteHandler()
        );
    }

    /**
     * Метод обаботки входящего сообщения
     * Парсит входящую текстовую строку, отделяя команду и находя отдельный обработчик для нее
     * @param message входящее сообщение
     * @return результат обработки сообщения
     */
    private String processInput(Message message) {
        String[] input = StringUtils.split(message.getText(), " ");
        if (input != null) {
            var handler = getHandler(input[0]);

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

    /**
     * Получить обработчик команды по префиксу
     * @param cmd префикс
     * @return экземпляр обработчика
     */
    private CommandHandler getHandler(String cmd) {
        CommandHandler hnd = commandMap.get(cmd);
        return hnd != null ? hnd : commandMap.get("/help");
    }

    /**
     * Заполнение набора команд бота для справки
     */
    private void fillCommandMap() {
        commandMap = new HashMap<>();
        for (CommandHandler hnd : handlers) {
            commandMap.put(hnd.getPrefix(), hnd);
        }

        commandMap.put("/help", new HandlerAbout(handlers));
    }

}
