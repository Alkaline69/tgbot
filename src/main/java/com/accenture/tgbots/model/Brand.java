package com.accenture.tgbots.model;

import lombok.Data;

@Data
public class Brand extends IdentifiedModel {

    String name;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name)
                .toString();
    }

}
