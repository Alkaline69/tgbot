package com.accenture.tgbots.sample;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class DbProperties {
    private String myemail;
}
