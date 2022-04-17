package com.example.telegrambot.appConfig;

import com.example.telegrambot.telegramFacade.TelegramFacade;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import javax.ws.rs.ext.ParamConverter;

@Getter
@Component
@Slf4j
public class TelegramBotGosha extends TelegramWebhookBot {
    private final TelegramFacade facade;
    @Lazy
    public TelegramBotGosha(TelegramFacade facade) {
        this.facade = facade;
    }

    @Value("${telegramBot.webHookPath}")
    private String webHookPath;
    @Value("${telegramBot.userName}")
    private String botUserName;
    @Value("${telegramBot.botToken}")
    private String botToken;



    @SneakyThrows
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

                log.info(String.format("Sent %s from %s", update.getMessage().getText(), update.getMessage().getChat().getFirstName()));
                facade.handleUpdate(update);

        return null;
    }


    @PostConstruct
    @SneakyThrows
    public void init() {

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this, SetWebhook.builder().url(this.webHookPath).build());
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
