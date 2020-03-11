package com.accenture.tgbots.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class NamedModel extends IdentifiedModel {

    String name;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name)
                .toString();
    }

}
