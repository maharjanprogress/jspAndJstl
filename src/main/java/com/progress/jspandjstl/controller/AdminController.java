package com.progress.jspandjstl.controller;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.service.implementation.ServiceRouter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final ServiceRouter serviceRouter;

    public AdminController(ServiceRouter serviceRouter) {
        this.serviceRouter = serviceRouter;
    }

    @PostMapping(value = "/admin/{type}")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable("type") String type) {
        List<TaskDTO> tasks = serviceRouter.getTaskService(type).getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
