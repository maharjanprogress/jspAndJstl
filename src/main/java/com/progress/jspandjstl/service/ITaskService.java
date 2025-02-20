package com.progress.jspandjstl.service;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.model.Tasks;

import java.util.Date;
import java.util.List;

public interface ITaskService {
    List<TaskDTO> getTasks();

    Tasks getTask(int Taskid);

    void addTask(Tasks Task);

    void deleteTask(int Taskid);

    String updateTask(Tasks Task);

    List<TaskDTO> getTasksWDate(Date parsedDate);
}
