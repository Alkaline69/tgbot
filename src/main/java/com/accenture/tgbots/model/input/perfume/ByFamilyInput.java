package com.accenture.tgbots.model.input.perfume;

import com.accenture.tgbots.model.input.HandlerInput;

public class ByFamilyInput implements HandlerInput {
    String family;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
