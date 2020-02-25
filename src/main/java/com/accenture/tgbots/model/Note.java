package com.accenture.tgbots.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
public class Note extends IdentifiedModel {

    String name;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name)
                .toString();
    }
}
