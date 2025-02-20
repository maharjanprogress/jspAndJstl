package com.progress.jspandjstl.service.implementation;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.model.Tasks;
import com.progress.jspandjstl.repository.TaskRepo;
import com.progress.jspandjstl.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("fonePay")
public class TaskService implements ITaskService {
    @Autowired
    TaskRepo repo;

    public List<TaskDTO> getTasks() {
        List<Tasks> tasks = repo.findAll();
        List<TaskDTO> taskDTO = new ArrayList<>();
        tasks.forEach(product -> {
            TaskDTO taskRepo = new TaskDTO();
            taskRepo.setTaskid(product.getTaskid());
            taskRepo.setTask(product.getTask());
            taskRepo.setDate(product.getDate());
            taskDTO.add(taskRepo);
        });
        return taskDTO;
    }

    // question:::: if the class Tasks is used in JPA repository then can a copy of Tasks ie TaskDTO be used as a type while retrieving data from repo
    public Tasks getTask(int Taskid) {
        return repo.findById(Taskid).orElse(new Tasks(Taskid, "noTask", new Date(2022, 02, 03)));
    }

    public void addTask(Tasks Task) {
        if (!repo.existsById(Task.getTaskid())) {
            repo.save(Task); // Save the task if it doesn't exist
        } else {
            System.out.println("Task with id " + Task.getTaskid() + " already exists.");
        }
    }

    public void deleteTask(int Taskid) {
        repo.deleteById(Taskid);
    }

    public String updateTask(Tasks Task) {
        if (repo.existsById(Task.getTaskid())) {
            repo.save(Task);
            return "Task updated successfully!";
        } else {
            return "Task with ID " + Task.getTaskid() + " does not exist!";
        }
    }

    @Override
    public List<TaskDTO> getTasksWDate(Date parsedDate) {
        List<Tasks> tasks = repo.findAll();
        List<TaskDTO> taskDTO = new ArrayList<>();
        tasks.forEach(task -> {
            if (task.getDate().equals(parsedDate)) {
                TaskDTO taskRepo = new TaskDTO();
                taskRepo.setTaskid(task.getTaskid());
                taskRepo.setTask(task.getTask());
                taskRepo.setDate(task.getDate());
                taskDTO.add(taskRepo);
            }
        });
        return taskDTO;
    }
//    List<Tasks> tasks = Arrays.asList(
//            new Tasks(1, "Do the first thing", "234234"),
//            new Tasks(2, "Do the second thing", "234234"),
//            new Tasks(3, "Do the third thing", "234234")
//    );
}
