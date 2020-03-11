package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.dto.product.Product;
import com.accenture.tgbots.model.input.perfume.NoviceInput;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;

import static org.jooq.generated.Tables.*;

public class ProductDao extends AbstractHandlerDao {

    private final static RecordMapper<Record, Product> MAPPER = record -> {
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
    };

    /**
     * Отдать список парфюмерных товаров
     * @return
     */
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
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);
            return products;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Отдать случайный товар
     * @return
     */
    public Product getRandomProduct() {
        try {
            int count = dsl().selectCount().from(TPRODUCT).fetchOne(0, int.class);
            int randomIndex = (int) (Math.random() * count);

            Product product = dsl()
                    .select()
                    .from(TPRODUCT)
                    .join(TBRAND).on(TBRAND.BRANDID.eq(TPRODUCT.BRANDID))
                    .join(TTYPE).on(TTYPE.TYPEID.eq(TPRODUCT.TYPEID))
                    .join(TFAMILY).on(TFAMILY.FAMILYID.eq(TPRODUCT.FAMILYID))
                    .join(TCOUNTRY).on(TCOUNTRY.COUNTRYID.eq(TPRODUCT.COUNTRYID))
                    .join(TSEX).on(TSEX.SEXID.eq(TPRODUCT.SEXID))
                    .limit(1)
                    .offset(randomIndex)
                    .fetchOne(MAPPER);
            return product;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Product> getByFamily(String family) {
        try {
            List<Product> products = dsl()
                    .select()
                    .from(TPRODUCT)
                    .join(TBRAND).on(TBRAND.BRANDID.eq(TPRODUCT.BRANDID))
                    .join(TTYPE).on(TTYPE.TYPEID.eq(TPRODUCT.TYPEID))
                    .join(TFAMILY).on(TFAMILY.FAMILYID.eq(TPRODUCT.FAMILYID))
                    .join(TCOUNTRY).on(TCOUNTRY.COUNTRYID.eq(TPRODUCT.COUNTRYID))
                    .join(TSEX).on(TSEX.SEXID.eq(TPRODUCT.SEXID))
                    .where(TFAMILY.NAME.likeIgnoreCase(family))
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);

            if (CollectionUtils.isEmpty(products)) {
                if (!dsl().fetchExists(TFAMILY, TFAMILY.NAME.likeIgnoreCase(family))) {
                    throw new RuntimeException("Введенного семейства не существует");
                }
            }

            return products;
        } catch (SQLException e) {
            return null;
        }
    }

    public Product getByNameAndBrand(String name, String brand) {
        try {
            return dsl()
                    .select()
                    .from(TPRODUCT)
                    .join(TBRAND).on(TBRAND.BRANDID.eq(TPRODUCT.BRANDID))
                    .join(TTYPE).on(TTYPE.TYPEID.eq(TPRODUCT.TYPEID))
                    .join(TFAMILY).on(TFAMILY.FAMILYID.eq(TPRODUCT.FAMILYID))
                    .join(TCOUNTRY).on(TCOUNTRY.COUNTRYID.eq(TPRODUCT.COUNTRYID))
                    .join(TSEX).on(TSEX.SEXID.eq(TPRODUCT.SEXID))
                    .where(
                            TPRODUCT.NAME.likeIgnoreCase(name)
                            .and(TBRAND.NAME.equalIgnoreCase(brand)))
                    .limit(MAX_ROWS)
                    .fetchOne(MAPPER);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Product> getByDiscount(Byte from, Byte to) {
        try {
            return dsl()
                    .select()
                    .from(TPRODUCT)
                    .join(TBRAND).on(TBRAND.BRANDID.eq(TPRODUCT.BRANDID))
                    .join(TTYPE).on(TTYPE.TYPEID.eq(TPRODUCT.TYPEID))
                    .join(TFAMILY).on(TFAMILY.FAMILYID.eq(TPRODUCT.FAMILYID))
                    .join(TCOUNTRY).on(TCOUNTRY.COUNTRYID.eq(TPRODUCT.COUNTRYID))
                    .join(TSEX).on(TSEX.SEXID.eq(TPRODUCT.SEXID))
                    .join(TDISCOUNT).on(TPRODUCT.PRODUCTID.eq(TDISCOUNT.PRODUCTID))
                    .orderBy(TDISCOUNT.DISCOUNT.desc())
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<Product> getByNovelty(NoviceInput noviceInput) {
        try {
            return dsl()
                    .select()
                    .from(TPRODUCT)
                    .join(TBRAND).on(TBRAND.BRANDID.eq(TPRODUCT.BRANDID))
                    .join(TTYPE).on(TTYPE.TYPEID.eq(TPRODUCT.TYPEID))
                    .join(TFAMILY).on(TFAMILY.FAMILYID.eq(TPRODUCT.FAMILYID))
                    .join(TCOUNTRY).on(TCOUNTRY.COUNTRYID.eq(TPRODUCT.COUNTRYID))
                    .join(TSEX).on(TSEX.SEXID.eq(TPRODUCT.SEXID))
                    .where(TPRODUCT.REALIZEDATE.ge(new java.sql.Date(noviceInput.getDepth().getDateFrom().getTime())))
                    .orderBy(TPRODUCT.REALIZEDATE.desc())
                    .limit(MAX_ROWS)
                    .fetch(MAPPER);
        } catch (SQLException e) {
            return null;
        }
    }
}
