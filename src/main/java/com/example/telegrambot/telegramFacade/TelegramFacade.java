package com.example.telegrambot.telegramFacade;

import com.example.telegrambot.appConfig.TelegramBotGosha;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
public class TelegramFacade {

    private final TelegramBotGosha bot;

    @Lazy
    public TelegramFacade(TelegramBotGosha bot) {
        this.bot = bot;
    }

    @SneakyThrows
    public SendMessage handleUpdate(Update update) {

                Message message = update.getMessage();

//                        if (message.getText().equals("/menu")) {
                            if (message.getText().equalsIgnoreCase("Валюты")) {

                        bot.execute(
                                SendMessage.builder()
                                        .chatId(message.getChatId().toString())
                                        .text("Ну и че ты тыкаешь на эту кнопку?")
                                        .build());

                                ReplyKeyboardMarkup keyboardForCurrency = new ReplyKeyboardMarkup();

                                keyboardForCurrency.setSelective(true);
                                keyboardForCurrency.setResizeKeyboard(true);
                                keyboardForCurrency.setOneTimeKeyboard(false);


                                List<KeyboardRow> keyboardCurrency = new ArrayList<>();
                                KeyboardRow row1 = new KeyboardRow();
                                KeyboardRow row2 = new KeyboardRow();
                                KeyboardRow row3 = new KeyboardRow();

                                row1.add(new KeyboardButton("Подписаться на Биткоин"));
                                row2.add(new KeyboardButton("Отписаться от Биткоина"));
                                row3.add(new KeyboardButton("Назад"));


                                keyboardCurrency.add(row1);
                                keyboardCurrency.add(row2);
                                keyboardCurrency.add(row3);

                                keyboardForCurrency.setKeyboard(keyboardCurrency);

                                bot.execute(
                                        SendMessage.builder()
                                                .chatId(message.getChatId().toString())
                                                .replyMarkup(keyboardForCurrency)
                                                .text(message.getFrom().getFirstName() + ", действуйте: \n\n")
                                                .build());



    }
        return null;
    }}