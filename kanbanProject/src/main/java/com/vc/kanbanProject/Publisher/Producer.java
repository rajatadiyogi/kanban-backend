package com.vc.kanbanProject.Publisher;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private DirectExchange directExchange;


    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMessageToQueue(ProjectDTO projectDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(), "key", projectDTO);
    }
}
