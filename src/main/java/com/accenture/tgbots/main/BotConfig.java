package com.accenture.tgbots.main;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

/**
 * Конфигурация бота
 */
@Data
public class BotConfig {

    @NotBlank
    @JsonProperty(required = true)
    private String token;

    @NotBlank
    @JsonProperty(required = true)
    private String username;

    @JsonProperty
    private String proxyHost;

    @JsonProperty
    private Integer proxyPort;

    @JsonProperty
    private String proxyUser;

    @JsonProperty
    private String proxyPassword;
}