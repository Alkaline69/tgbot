package com.accenture.tgbots.model.input.perfume;

import com.accenture.tgbots.model.input.HandlerInput;

public class GetNotesInput implements HandlerInput {

    String brand;

    String product;

    Boolean allNotes;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Boolean getAllNotes() {
        return allNotes;
    }

    public void setAllNotes(Boolean allNotes) {
        this.allNotes = allNotes;
    }
}
