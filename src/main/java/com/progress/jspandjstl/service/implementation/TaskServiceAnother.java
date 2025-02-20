package com.progress.jspandjstl.service.implementation;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.model.Tasks;
import com.progress.jspandjstl.repository.RanjanRepo;
import com.progress.jspandjstl.repository.TaskRepo;
import com.progress.jspandjstl.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("esewa")
public class TaskServiceAnother implements ITaskService {
    @Autowired
    RanjanRepo repo;

    @Override
    public List<TaskDTO> getTasks() {
        TaskDTO tasks = new TaskDTO();
        tasks.setTask("esewa");
        return Collections.singletonList(tasks);
    }

    @Override
    public Tasks getTask(int Taskid) {
        return null;
    }

    @Override
    public void addTask(Tasks Task) {

    }

    @Override
    public void deleteTask(int Taskid) {

    }

    @Override
    public String updateTask(Tasks Task) {
        return "";
    }

    @Override
    public List<TaskDTO> getTasksWDate(Date parsedDate) {
        return List.of();
    }
}
