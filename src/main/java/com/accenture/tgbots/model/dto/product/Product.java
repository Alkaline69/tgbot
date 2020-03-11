package com.accenture.tgbots.model.dto.product;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

public class Product {

    @Column(name = "productID")
    private Integer productID;

    private String name;

    private BigDecimal cost;

    private Byte vol;

    @Column(name = "realizeDate")
    private Date realizeDate;

    private String brand;

    private String type;

    private String family;

    private String country;

    private String sex;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Byte getVol() {
        return vol;
    }

    public void setVol(Byte vol) {
        this.vol = vol;
    }

    public Date getRealizeDate() {
        return realizeDate;
    }

    public void setRealizeDate(Date realizeDate) {
        this.realizeDate = realizeDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

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
