package com.example.telegrambot.telegramFacade;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ITelegramFacade {
     SendMessage handleUpdate(Update update);
}
