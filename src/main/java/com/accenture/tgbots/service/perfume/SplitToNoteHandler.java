package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.NoteDao;
import com.accenture.tgbots.model.Note;
import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.input.HandlerInput;
import com.accenture.tgbots.model.input.perfume.ByFamilyInput;
import com.accenture.tgbots.model.input.perfume.GetNotesInput;
import com.accenture.tgbots.service.CommandHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitToNoteHandler implements CommandHandler {

    private final NoteDao noteDao = new NoteDao();

    @Override
    public String getPrefix() {
        return "/get_notes_by_name";
    }

    @Override
    public String getDescription() {
        return "разложить введённый парфюм на ноты";
    }

    @Override
    public ProcessingResult process(HandlerInput args) {
        GetNotesInput input = (GetNotesInput) args;

        if (BooleanUtils.isTrue(input.getAllNotes())) {
            return new ProcessingResult(
                    noteDao.list().stream().map(Note::toString).collect(Collectors.toList())
            );
        } else {
            //todo
        }


        return null;
    }

    @Override
    public HandlerInput parseInputMessage(Message message) {
        GetNotesInput model = new GetNotesInput();
        String[] input = StringUtils.split(message.getText(), " ");
        if (input != null) {
            List<String> args = Arrays.asList(input).subList(1, input.length);
            if (args.size() == 0) {
                model.setAllNotes(true);
            } else if (args.size() == 2) {
                model.setBrand(args.iterator().next());
                model.setProduct(args.iterator().next());
            } else {
                throw new RuntimeException("Повторите команду с уточнением запроса");
            }
        }

        return model;
    }
}
