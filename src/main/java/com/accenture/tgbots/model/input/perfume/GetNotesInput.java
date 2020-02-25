package com.accenture.tgbots.model.input.perfume;

import com.accenture.tgbots.model.input.HandlerInput;
import lombok.Data;

@Data
public class GetNotesInput implements HandlerInput {

    String brand;

    String product;

    Boolean allNotes;

}
