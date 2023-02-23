package com.project.NotificationService.Subcriber;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    @Bean
    public Jackson2JsonMessageConverter getConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
