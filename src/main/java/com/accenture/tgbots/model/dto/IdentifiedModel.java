package com.accenture.tgbots.model.dto;

/**
 * Родитель с идентификатором из БД в иерархии моделей данных
 */
public abstract class IdentifiedModel {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
