package com.example.jmsdemo.sender;

import com.example.jmsdemo.config.JmsConfig;
import com.example.jmsdemo.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.info("I am sending a message");
        var message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello World!").build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        log.info("Message sent!");
    }
}
