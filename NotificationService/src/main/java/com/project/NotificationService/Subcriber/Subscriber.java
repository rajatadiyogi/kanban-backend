package com.project.NotificationService.Subcriber;


import com.project.NotificationService.execption.ProjectAlreadyExist;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {


    @RabbitListener(queues = "queue_name")
    public void getDetailsFromProducer(ProjectDTO projectDTO) throws ProjectAlreadyExist {

    }
}
