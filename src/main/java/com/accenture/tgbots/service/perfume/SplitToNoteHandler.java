package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.NoteDao;
import com.accenture.tgbots.dao.ProductDao;
import com.accenture.tgbots.model.Note;
import com.accenture.tgbots.model.ProcessingResult;
import com.accenture.tgbots.model.Product;
import com.accenture.tgbots.model.input.perfume.GetNotesInput;
import com.accenture.tgbots.service.CommandHandler;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitToNoteHandler implements CommandHandler<GetNotesInput> {

    private final NoteDao noteDao = new NoteDao();
    private final ProductDao productDao = new ProductDao();

    @Override
    public String getPrefix() {
        return "/get_notes_by_name";
    }

    @Override
    public String getDescription() {
        return "разложить введённый парфюм на ноты";
    }

    @Override
    public ProcessingResult process(GetNotesInput args) {
        if (BooleanUtils.isTrue(args.getAllNotes())) {
            return new ProcessingResult(
                    noteDao.list().stream().map(Note::toString).collect(Collectors.toList())
            );
        } else {

            Product product = productDao.getByNameAndBrand(args.getProduct(), args.getBrand());
            if (product != null) {
                return new ProcessingResult(
                        noteDao.listByProduct(product.getProductID()).stream().map(Note::toString).collect(Collectors.toList()));
            }

            //todo
        }


        return null;
    }

    @Override
    public GetNotesInput parseInputMessage(Message message) {
        GetNotesInput model = new GetNotesInput();

        String inputText = message.getText().replaceFirst(getPrefix(), "");

        String[] input = StringUtils.split(inputText, ",");
        if (input != null) {
            List<String> args = Arrays.asList(input);
            if (args.size() == 0) {
                model.setAllNotes(true);
            } else if (args.size() == 2) {
                model.setProduct(args.get(0).trim());
                model.setBrand(args.get(1).trim());
            } else {
                throw new RuntimeException("Повторите команду с уточнением запроса");
            }
        }

        return model;
    }
}
