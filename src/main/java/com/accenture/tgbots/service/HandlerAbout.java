package com.accenture.tgbots.service;

import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.NoArgsHandlerInput;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collections;
import java.util.List;

/**
 * Обработчик получения справки по командам бота
 */
public class HandlerAbout implements CommandHandler<NoArgsHandlerInput> {

    private final String help;

    public HandlerAbout(List<CommandHandler> handlers) {
        help = getAbout(handlers);
    }

    @Override
    public String getPrefix() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "/about \tПолучить справку";
    }

    @Override
    public ProcessingResult process(NoArgsHandlerInput args) {
        String result = help;

        return new ProcessingResult(Collections.singletonList(result));
    }

    @Override
    public NoArgsHandlerInput parseInputMessage(Message message) {
//        String result = (args.size() > 1)
//                ? help + getGreeting(args.iterator().next())
//                : help;
        return null;
    }

    private String getAbout(List<CommandHandler> handlers) {
        StringBuilder sb  = new StringBuilder();
        sb.append("\nAromaBot поможет подобрать вам аромат по нотам, подобрать похожие ароматы при указании марки парфюма, отобразить парфюм со скидкой и многое другое!\n");

        sb.append("\nПоддерживаемые ботом команды:");
        sb.append("\n/about Вывод справки");
        for (CommandHandler hnd : handlers) {
            sb.append("\n").append(hnd.getPrefix()).append("\t").append(hnd.getDescription());
        }

        return sb.toString();
    }

    private String getGreeting(String name) {
        return new StringBuilder("Приветствую, ").append(name).append("!")
                .append(help)
                .toString();
    }
}
