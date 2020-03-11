package com.accenture.tgbots.service.perfume;

import com.accenture.tgbots.dao.NoteDao;
import com.accenture.tgbots.dao.ProductDao;
import com.accenture.tgbots.model.Note;
import com.accenture.tgbots.model.output.ProcessingResult;
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
            if (product == null) {
                product = productDao.getByNameAndBrand(args.getBrand(), args.getProduct());
            }

            if (product != null) {
                return new ProcessingResult(
                        noteDao.listByProduct(product.getProductID()).stream().map(Note::toString).collect(Collectors.toList()));
            } else {
                throw new RuntimeException("Повторите команду с уточнением запроса");
            }
        }
    }

    @Override
    public GetNotesInput parseInputMessage(Message message) {
        String inputText = StringUtils.remove(message.getText(), getPrefix());
        List<String> args = Arrays.stream(StringUtils.split(inputText,","))
                .map(String::trim)
                .collect(Collectors.toList());

        GetNotesInput model = new GetNotesInput();
        if (args.size() == 0) {
            model.setAllNotes(true);
        } else if (args.size() == 2) {
            model.setProduct(args.get(0));
            model.setBrand(args.get(1));
        } else {
            throw new RuntimeException("Повторите команду с уточнением запроса");
        }

        return model;
    }
}
