package com.accenture.tgbots.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    @Column(name = "productID")
    Integer productID;

    String name;

    BigDecimal cost;

    Byte vol;

    @Column(name = "realizeDate")
    Date realizeDate;

    String brand;
    String type;
    String family;
    String country;
    String sex;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(brand).append(", ")
                .append(name).append(", ")
//                .append(vol).append(", ")
                .append(type).append(", ")
                .append(realizeDate).append(", ")
                .append(cost)
//                .append(family).append(",")
//                .append(country).append(",")
//                .append(sex)
                .toString();
    }
}
