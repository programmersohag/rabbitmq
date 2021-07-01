package com.example.rabbitmq.service;

import com.example.rabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private final AmqpTemplate amqpTemplate;

    public RabbitMqService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * Sends messages via Topic exchange
     * @param message -message
     */
    public void sendViaTopicExchange(String message) {
        // convertAndSend takes the exchange name, routing-key, and the message
        amqpTemplate.convertAndSend(RabbitMqConfig.topicExchangeName, "demo.test", message);
    }

    /**
     * sends messages via Headers exchange
     * @param message -message
     */
    public void sendViaHeadersExchange(String message) {
        // creates a message object and set headers
        Message sysErrMsg = MessageBuilder.withBody(message.getBytes()).setHeader("testId", "123")
                .setHeader("anotherId", "another123").build();
        amqpTemplate.convertAndSend(RabbitMqConfig.headerExchangeName, "header.#", sysErrMsg);

    }

    /**
     * sends messages via Direct exchange
     * @param message -message
     */
    public void sendViaDirectExchange(String message) {
        amqpTemplate.convertAndSend(RabbitMqConfig.directExchangeName, "direct", message);

    }

    /**
     * sends messages via Fanout exchange
     * @param message -message
     */
    public void sendViaFanoutExchange(String message) {
        amqpTemplate.convertAndSend(RabbitMqConfig.fanoutExchangeName," " ,message);

    }

}
