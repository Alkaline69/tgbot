package com.accenture.tgbots.model.output;

import java.util.Collections;
import java.util.List;

/**
 * Результат оюработки ботом команды
 */
public class ProcessingResult {

    public enum Status {OK, BAD_ARGUMENT, INTERNAL_ERROR}

    /**
     * Статус результата
     */
    private Status status;

    /**
     * Список строк, отдаваемых в результате работы команды
     */
    private List<String> results;

    public ProcessingResult() {
    }

    public ProcessingResult(String result) {
        this(Collections.singletonList(result));
    }

    public ProcessingResult(List<String> results) {
        status = Status.OK;
        this.results = results;
    }

    public boolean isOk() {
        return Status.OK == status;
    }

    public String getError() {
        return status.name();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
