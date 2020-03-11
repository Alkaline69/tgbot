package com.accenture.tgbots.service;

import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Интерфейс обработчика отдельной команды бота
 * @param <T> набор аргументов и входных параметров команды
 */
public interface CommandHandler<T extends HandlerInput> {

    /**
     * Текстовое обозначение команды
     * @return
     */
    String getPrefix();

    /**
     * Описание команды (используется для вывода справки)
     * @return
     */
    String getDescription();

    /**
     * Логика обработки команды
     * @param args аргументы команды
     * @return
     */
    ProcessingResult process(T args);

    /**
     * Разобрать входящее сообщение команде на специфичные параметры
     * @param message входящее сообщение боту
     * @return
     */
    default T parseInputMessage(Message message) {
        return null;
    }

}
