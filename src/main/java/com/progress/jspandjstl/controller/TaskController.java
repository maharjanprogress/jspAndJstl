package com.progress.jspandjstl.controller;

import com.progress.jspandjstl.model.TaskDTO;
import com.progress.jspandjstl.model.Tasks;
import com.progress.jspandjstl.service.ITaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    @Qualifier("fonePay")
    ITaskService service;
    @GetMapping("/")
    public ResponseEntity<List<TaskDTO>> getTasks(){
        return new ResponseEntity<>(service.getTasks(), HttpStatus.OK);
    }


    @RequestMapping("/call")
    public String getlogin(HttpServletRequest request) {
        // Get the session object
        HttpSession session = request.getSession();

        // Pass the session ID as an attribute to the JSP page
        request.setAttribute("sessionId", session.getId());
        return "login";
    }

    @GetMapping("/tasks/add")
    public String showAddTaskForm() {
        return "add";  // Navigate to add.jsp page
    }

    @GetMapping("/tasks")
    public String getAllTasks(Model model) {
        // Fetch all tasks as DTOs
        List<TaskDTO> tasks = service.getTasks();

        // Add the tasks to the model so they are accessible in the JSP
        model.addAttribute("tasks", tasks);

        // Return the view name (home.jsp)
        return "home";
    }

    @GetMapping("/tasks/modify/{taskId}")
    public String modifyTask(@PathVariable int taskId, Model model) {
        Tasks task = service.getTask(taskId);
        model.addAttribute("task", task);
        return "modify";  // A JSP page for task modification
    }

    @PostMapping("/tasks/update")
    public String updatesTask(@RequestParam("taskid") int taskId,
                             @RequestParam("task") String taskName,
                             @RequestParam("date") String dateStr) {

        // Define the formatter for the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        try {
            // Parse the string date to ZonedDateTime
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, formatter);

            // Convert ZonedDateTime to Date
            Date parsedDate = Date.from(zonedDateTime.toInstant());
            Tasks newTask = new Tasks(taskId, taskName, parsedDate);
            service.updateTask(newTask);
            return "redirect:/tasks";
        } catch (Exception e) {
            // Handle the exception if the format is incorrect
            e.printStackTrace();
        }
        return "redirect:/tasks";  // Redirect to task list page
    }

    @PostMapping(value = "/tasks/save")
    public String addsTask(@RequestParam("taskid") int taskId,
                              @RequestParam("task") String taskName,
                              @RequestParam("date") String dateStr) {

        // Define the formatter for the required format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        try {
            // Parse the string date to ZonedDateTime
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, formatter);

            // Convert ZonedDateTime to Date
            Date parsedDate = Date.from(zonedDateTime.toInstant());
            Tasks newTask = new Tasks(taskId, taskName, parsedDate);
            service.addTask(newTask);
            return "redirect:/tasks";
        } catch (Exception e) {
            // Handle the exception if the format is incorrect
            e.printStackTrace();
        }
        return "redirect:/tasks";  // Redirect to task list page
    }

    @PostMapping(value = "/taskWithDate", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<List<TaskDTO>> getTaskWDate(@RequestParam String date){
        Date parsedDate;
        if (!date.isEmpty()){
            try {
                parsedDate = new Date(date);
            }
            catch (DateTimeParseException e) {
                return new ResponseEntity<>(Arrays.asList(new TaskDTO(400, "The format should be (yyyy-mm-dd)",new Date() )),HttpStatus.BAD_REQUEST);
            }
        }
        else {
            parsedDate = new Date();
        }
        return new ResponseEntity<>(service.getTasksWDate(parsedDate), HttpStatus.OK);
    }
    @GetMapping("/csrf")
    @ResponseBody
    public CsrfToken csrf(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @GetMapping("/{taskid}")
    public Tasks getTaskById(@PathVariable int taskid){
        return service.getTask(taskid);
    }
    @DeleteMapping("/{taskid}")
    public void deleteTask(@PathVariable int taskid){
        service.deleteTask(taskid);
    }
    @GetMapping("/tasks/delete/{taskId}")
    public String deletTask(@PathVariable int taskId) {
        service.deleteTask(taskId);
        return "redirect:/tasks";  // A JSP page for task modification
    }
    @PutMapping(value = "/", consumes = "application/x-www-form-urlencoded")
    public String updateTask(@RequestParam int taskid,@RequestParam String task,@RequestParam String date){
        // Parse the date from the string into LocalDate
        LocalDate parsedDate = LocalDate.parse(date);

        // Create a new task
        Tasks newTask = new Tasks(taskid, task, new Date(date));
        return service.updateTask(newTask);
    }
//    @PutMapping("/")
//    public String updateTask(@RequestBody Tasks product){
//        return service.updateTask(product);
//    }


    @PostMapping(value = "/", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> addTask(@RequestParam int taskid,@RequestParam String task,@RequestParam String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        try {
            // Parse the string date to ZonedDateTime
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);

            // Convert ZonedDateTime to Date
            Date parsedDate = Date.from(zonedDateTime.toInstant());

            // Create a new task with the parsed date
            Tasks newTask = new Tasks(taskid, task, parsedDate);
            service.addTask(newTask);
            int x = 2,y=3,z=3;
            return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully!");

        } catch (Exception e) {
            // Handle the exception and return an error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format!");
        }
        // Create a new task
//        Tasks newTask = new Tasks(taskid, task, new Date(date));
//        service.addTask(newTask);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully!");
//        System.out.println(product);
//        service.addTask(product);
    }
}