package com.accenture.tgbots.sample;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

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