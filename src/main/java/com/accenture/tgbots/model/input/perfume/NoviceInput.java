package com.accenture.tgbots.model.input.perfume;

import com.accenture.tgbots.model.input.HandlerInput;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class NoviceInput implements HandlerInput {

    /**
     * Глубина поиска по истории
     */
    public enum Depth {
        ONE_MONTH {
            @Override
            public Date getDateFrom() {
                return DateUtils.addMonths(new Date(), -1);
            }
        },
        THREE_MONTH {
            @Override
            public Date getDateFrom() {
                return DateUtils.addMonths(new Date(), -3);
            }
        },
        SIX_MONTH {
            @Override
            public Date getDateFrom() {
                return DateUtils.addMonths(new Date(), -6);
            }
        },
        LAST_YEAR {
            @Override
            public Date getDateFrom() {
                return DateUtils.truncate(new Date(), Calendar.YEAR);
            }
        },
        ;

        public abstract Date getDateFrom();
    }

    public NoviceInput(Depth depth) {
        this.depth = depth;
    }

    Depth depth;

    public Depth getDepth() {
        return depth;
    }

    public void setDepth(Depth depth) {
        this.depth = depth;
    }
}
