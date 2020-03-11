package com.accenture.tgbots.model.dto;

/**
 * Именованная модель данных справочника
 */
public abstract class NamedModel extends IdentifiedModel {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(name)
                .toString();
    }

}
