package com.accenture.tgbots.model.input.perfume;

import com.accenture.tgbots.model.input.HandlerInput;
import lombok.Data;

@Data
public class ByFamilyInput implements HandlerInput {
    String family;
}
