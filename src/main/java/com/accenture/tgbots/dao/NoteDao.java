package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.dto.dict.Note;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.generated.Tables.*;

public class NoteDao extends AbstractHandlerDao {

    private final static RecordMapper<Record, Note> MAPPER = record -> {
        Note note = new Note();
        note.setId(record.get(TNOTE.NOTEID));
        note.setName(record.get(TNOTE.NAME));
        return note;
    };

    public List<Note> list() {
        try {
            List<Note> products = dsl()
                    .select()
                    .from(TNOTE)
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);
            return products;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Note> listByProduct(Integer productId) {
        try {
            List<Note> products = dsl()
                    .select()
                    .from(TNOTE)
                    .join(TPRODUCT).on(TPRODUCT.PRODUCTID.eq(TPRODUCTNOTES.PRODUCT_ID))
                    .join(TPRODUCTNOTES).on(TPRODUCTNOTES.NOTE_ID.eq(TNOTE.NOTEID))
                    .where(TPRODUCT.PRODUCTID.eq(productId))
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);
            return products;
        } catch (SQLException e) {
            return null;
        }
    }

}
