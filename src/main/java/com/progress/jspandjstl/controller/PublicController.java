package com.progress.jspandjstl.controller;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.service.ITaskService;
import com.progress.jspandjstl.service.implementation.ServiceRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class PublicController {
//    @Autowired
//    @Qualifier("taskService")
//    ITaskService service;
    private final ServiceRouter serviceRouter;
    public PublicController(ServiceRouter serviceRouter) {
        this.serviceRouter = serviceRouter;
    }
    @GetMapping("/public/{type}")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable String type) {
        return new ResponseEntity<>(serviceRouter.getTaskService(type).getTasks(), HttpStatus.OK);
    }

}
