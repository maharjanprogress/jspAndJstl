package com.progress.jspandjstl.service.implementation;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.model.Tasks;
import com.progress.jspandjstl.service.ITaskService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("khalti")
public class TaskServiceEsewa implements ITaskService {

    @Override
    public List<TaskDTO> getTasks() {
        TaskDTO tasks = new TaskDTO();
        tasks.setTask("Khalti");
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
