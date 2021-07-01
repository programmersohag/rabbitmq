package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String queueName = "demo-rabbitmq";
    public static final String topicExchangeName = "topic-exchange";
    public static final String headerExchangeName = "demo-headers-exchange";
    public static final String directExchangeName = "demo-direct-exchange";
    public static final String fanoutExchangeName = "demo-fanout-exchange";

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    HeadersExchange headers() {
        return new HeadersExchange(headerExchangeName);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(directExchangeName);
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    //Bind topic exchange to a queue
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        // bind a queue to a Topic Exchange with routing key
        return BindingBuilder.bind(queue).to(exchange).with("demo.#");
    }

    //Bind header exchange to a queue
    @Bean
    Binding bindingHeader(Queue queue, HeadersExchange exchange) {
        // bind a queue to a Header Exchange with argument testId is exist
        return BindingBuilder.bind(queue).to(exchange).where("testId").exists();
    }

    //Bind topic direct exchange to a queue
    @Bean
    Binding directExchangeBinding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with("direct");
    }

    //Bind topic fanout exchange to a queue
    @Bean
    Binding bindingFanout(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
