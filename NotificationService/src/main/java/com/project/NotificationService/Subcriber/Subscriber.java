package com.project.NotificationService.Subcriber;





//import com.vc.kanbanProject.service.KanbanService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Subscriber {

//    @Autowired
//    private KanbanService kanbanService;

//    @RabbitListener(queues = "queue_name")
//    public void getDetailsFromProducer(ProjectDTO projectDTO) throws EmployeeNotFound {
//
//        Project project = new Project();
//        Project employee = new Project();
//        List<Task> list = new ArrayList<>();
//        //list.add(employee);
//        employee.setTaskList(list);
////        Employee emp = kanbanService.saveProjectToList(project, employee.getEmail());
//    }
}
