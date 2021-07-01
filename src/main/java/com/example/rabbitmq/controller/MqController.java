package com.example.rabbitmq.controller;

import com.example.rabbitmq.service.RabbitMqService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MqController {

    private final RabbitMqService rabbitMqService;

    public MqController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendTopic() {
        String message = "this is a topic exchange demo";
        rabbitMqService.sendViaTopicExchange(message);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping(value = "/send-headers")
    public ResponseEntity<String> sendHeaders() {
        String message = "this is a headers exchange demo";
        rabbitMqService.sendViaHeadersExchange(message);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping(value = "/send-direct")
    public ResponseEntity<String> sendDirect() {
        String message = "this is a direct exchange demo";
        rabbitMqService.sendViaDirectExchange(message);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping(value = "/send-fanout")
    public ResponseEntity<String> sendFanout() {
        String message = "this is a fanout exchange demo";
        rabbitMqService.sendViaFanoutExchange(message);
        return ResponseEntity.ok().body("Success");
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    /**
     * recive messages from rabbitMq queue
     * @param incomingMessage
     */
    public void receivedMessage(String incomingMessage) {
        System.out.println("Received Message From RabbitMQ: " + incomingMessage);
    }
}
