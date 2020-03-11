package com.accenture.tgbots;

import com.annimon.tgbotsmodule.BotHandler;
import com.annimon.tgbotsmodule.BotModule;
import com.annimon.tgbotsmodule.Runner;
import com.annimon.tgbotsmodule.beans.Config;
import com.annimon.tgbotsmodule.services.YamlConfigLoaderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class AromaBot implements BotModule {

    @Override
    public @NotNull BotHandler botHandler(@NotNull Config config) {
        final var configLoader = new YamlConfigLoaderService<BotConfig>();
        final var configFile = configLoader.configFile("botconfig", config.getProfile());
        final var botConfig = configLoader.load(configFile, BotConfig.class);
        return new BotHandlerImpl(botConfig);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AromaBot.class);
        app.run();

        final String profile = (args.length >= 1 && !args[0].isEmpty()) ? args[0] : "";
        Runner.run(profile, Collections.singletonList(new AromaBot()));
    }

}
