package com.smtp.EmailNotification.rabbitmq;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
//    private String exchangeName = "kanban-exchange";
//    private String queue = "email-notification";
//
//    @Bean
//    public DirectExchange directExchange(){return new DirectExchange(exchangeName);}
//    @Bean
//    public Queue queue(){return new Queue(queue);}

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){ return new Jackson2JsonMessageConverter();}

//    @Bean
//    Binding bindingEmail(DirectExchange exchange){
//        return BindingBuilder.bind(queue()).to(exchange).with("email-routing");
//    }
}
