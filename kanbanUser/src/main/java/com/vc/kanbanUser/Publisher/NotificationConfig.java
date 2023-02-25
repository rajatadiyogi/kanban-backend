package com.vc.kanbanUser.Publisher;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class NotificationConfig {
//
//    private String exchange_type = "direct";
//    private String queue_message = "queue_name";
//
//
//    @Bean
//    public DirectExchange getDirectExchange(){
//        return new DirectExchange(exchange_type);
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter converter(){
//        return  new Jackson2JsonMessageConverter();
//    }
//
//
//    @Bean
//    public Queue queues(){
//        return new Queue(queue_message);
//    }
//
//    @Bean
//    public RabbitTemplate convertMessageToJson(final ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(converter());
//
//        return rabbitTemplate;
//    }
//
//    @Bean
//    public Binding getBinding(Queue queue, DirectExchange directExchange){
//        return BindingBuilder.bind(queue).to(directExchange).with("key");
//    }

}
