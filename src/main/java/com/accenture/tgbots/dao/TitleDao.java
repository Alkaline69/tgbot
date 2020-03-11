package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.dto.dict.Title;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.generated.Tables.TPRODUCT;

public class TitleDao extends AbstractHandlerDao {

    private final static RecordMapper<Record, Title> MAPPER = record -> {
        Title title = new Title();
        title.setId(record.get(TPRODUCT.PRODUCTID));
        title.setName((record.get(TPRODUCT.NAME)));
        return title;
    };

    public List<Title> list() {
        try {
            return dsl()
                    .select(TPRODUCT.PRODUCTID, TPRODUCT.NAME)
                    .from(TPRODUCT)
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
