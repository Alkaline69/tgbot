package com.accenture.tgbots.service.perfume.dict;

import com.accenture.tgbots.dao.NoteDao;
import com.accenture.tgbots.model.dto.dict.Note;
import com.accenture.tgbots.model.output.ProcessingResult;
import com.accenture.tgbots.model.input.NoArgsHandlerInput;
import com.accenture.tgbots.service.CommandHandler;

import java.util.stream.Collectors;

public class NoteHandler implements CommandHandler<NoArgsHandlerInput> {

    private final NoteDao noteDao = new NoteDao();

    @Override
    public String getPrefix() {
        return "/notes";
    }

    @Override
    public String getDescription() {
        return "Получить полный перечень доступных нот";
    }

    @Override
    public ProcessingResult process(NoArgsHandlerInput args) {
        return new ProcessingResult(noteDao.list()
                .stream()
                .map(Note::getName)
                .collect(Collectors.toList())
        );
    }
}
