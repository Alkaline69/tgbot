package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.Product;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.jooq.generated.Tables.*;

public class HandlerProductDao extends AbstractHandlerDao {

    public List<Product> getAllProducts() {
        try {
            List<Product> products = dsl()
                    .select()
                    .from(TPRODUCT)
                    .join(TBRAND).on(TBRAND.BRANDID.eq(TPRODUCT.BRANDID))
                    .join(TTYPE).on(TTYPE.TYPEID.eq(TPRODUCT.TYPEID))
                    .join(TFAMILY).on(TFAMILY.FAMILYID.eq(TPRODUCT.FAMILYID))
                    .join(TCOUNTRY).on(TCOUNTRY.COUNTRYID.eq(TPRODUCT.COUNTRYID))
                    .join(TSEX).on(TSEX.SEXID.eq(TPRODUCT.SEXID))
                    .limit(20)
                    .fetch(new RecordMapper<Record, Product>() {
                        @Override
                        public Product map(Record record) {
                            Product p = new Product();
                            p.setProductID(record.get(TPRODUCT.PRODUCTID));
                            p.setName(record.get(TPRODUCT.NAME));
                            p.setCost(record.get(TPRODUCT.COST));
                            p.setRealizeDate(record.get(TPRODUCT.REALIZEDATE));
                            p.setVol(record.get(TPRODUCT.VOL));
                            p.setBrand(record.get(TBRAND.NAME));
                            p.setType(record.get(TTYPE.NAME));
                            p.setFamily(record.get(TFAMILY.NAME));
                            p.setCountry(record.get(TCOUNTRY.NAME));
                            p.setSex(record.get(TSEX.NAME));
                            return p;
                        }
                    });

            return products;
        } catch (SQLException e) {
            return null;
        }
    }

    public Product getRandomProduct() {
        try {
            int count = dsl().selectCount().from(TPRODUCT).fetchOne(0, int.class);
            int randomIndex = (int) (Math.random() * count);
            return getAllProducts().get(randomIndex);
        } catch (SQLException e) {
            return null;
        }
    }
}
