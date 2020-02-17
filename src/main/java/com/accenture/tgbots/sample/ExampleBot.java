package com.accenture.tgbots.sample;

import com.annimon.tgbotsmodule.BotHandler;
import com.annimon.tgbotsmodule.BotModule;
import com.annimon.tgbotsmodule.Runner;
import com.annimon.tgbotsmodule.beans.Config;
import com.annimon.tgbotsmodule.services.YamlConfigLoaderService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import java.util.Collections;

@SpringBootApplication
public class ExampleBot implements BotModule {

    @Autowired
    private DbProperties dbProperties;

    private String justTest;

    @Override
    public @NotNull BotHandler botHandler(@NotNull Config config) {
        final var configLoader = new YamlConfigLoaderService<BotConfig>();
        final var configFile = configLoader.configFile("testbot", config.getProfile());
        final var botConfig = configLoader.load(configFile, BotConfig.class);


        // Set up Http proxy
//        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials(
//                new AuthScope(botConfig.getProxyHost(), botConfig.getProxyPort()),
//                new UsernamePasswordCredentials(botConfig.getProxyUser(), botConfig.getProxyPassword()));
//
//        HttpHost httpHost = new HttpHost(botConfig.getProxyHost(), botConfig.getProxyPort());
//
//        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(true).build();
//        botOptions.setRequestConfig(requestConfig);
//        botOptions.setCredentialsProvider(credsProvider);
//        botOptions.setHttpProxy(httpHost);

        return new BotHandlerImpl(botConfig);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ExampleBot.class);
        app.run();

        final String profile = (args.length >= 1 && !args[0].isEmpty()) ? args[0] : "";
        Runner.run(profile, Collections.singletonList(new ExampleBot()));
    }

    public void run(String... args) {


        final String profile = (args.length >= 1 && !args[0].isEmpty()) ? args[0] : "";
        Runner.run(profile, Collections.singletonList(new ExampleBot()));
    }
}
