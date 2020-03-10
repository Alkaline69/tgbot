package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.Brand;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.generated.Tables.TBRAND;
import static org.jooq.generated.Tables.TFAMILY;

public class BrandDao extends AbstractHandlerDao {

    private final static RecordMapper<Record, Brand> MAPPER = record -> {
        Brand brand = new Brand();
        brand.setId(record.get(TFAMILY.FAMILYID));
        brand.setName(record.get(TFAMILY.NAME));
        return brand;
    };

    public List<Brand> list() {
        try {
            return dsl().select().from(TBRAND).limit(MAX_ROWS).fetch(MAPPER);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
