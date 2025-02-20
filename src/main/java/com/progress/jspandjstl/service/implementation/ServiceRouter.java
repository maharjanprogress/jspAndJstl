package com.progress.jspandjstl.service.implementation;

import com.progress.jspandjstl.service.ITaskService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServiceRouter {
    private final Map<String, ITaskService> taskServices;

    public ServiceRouter(ApplicationContext applicationContext) {
        this.taskServices = applicationContext.getBeansOfType(ITaskService.class);
    }

    public ITaskService getTaskService(String type) {
        ITaskService service = taskServices.get(type);
        if (service == null) {
            throw new IllegalArgumentException("Task service " + type + " not found");
        }
        return service;
    }

}
