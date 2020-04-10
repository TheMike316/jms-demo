package com.example.jmsdemo.listener;

import com.example.jmsdemo.config.JmsConfig;
import com.example.jmsdemo.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

        log.info("I got a message!");
        log.info(helloWorldMessage.toString());
    }

    @JmsListener(destination = JmsConfig.MY_REQUEST_RESPONSE_QUEUE)
    public void listen(@Payload HelloWorldMessage payload,
                       Message message) throws JMSException {
        log.info("I got a message that i'm gonna reply to!");
        var response = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), response);

        log.info("Sent reply!");
    }
}
