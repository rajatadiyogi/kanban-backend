package com.project.NotificationService.execption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Project Already Exists")
public class ProjectAlreadyExist extends Exception{
}
