package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.ApplicationConfig;
import ru.otus.homework.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final ApplicationConfig config;

    @Override
    public String createMessage(String messageCode, String... args) {
        return messageSource.getMessage(messageCode, args, config.getLocale());
    }
}
