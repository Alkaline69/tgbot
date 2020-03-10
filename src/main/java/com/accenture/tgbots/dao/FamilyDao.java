package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.Family;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.generated.Tables.TFAMILY;

public class FamilyDao extends AbstractHandlerDao {

    private final static RecordMapper<Record, Family> MAPPER = record -> {
        Family family = new Family();
        family.setId(record.get(TFAMILY.FAMILYID));
        family.setName(record.get(TFAMILY.NAME));
        return family;
    };

    public List<Family> list() {
        try {
            return dsl().select().from(TFAMILY).limit(MAX_ROWS).fetch(MAPPER);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
