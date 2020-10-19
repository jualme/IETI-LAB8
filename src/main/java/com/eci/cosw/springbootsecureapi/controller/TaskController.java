package com.eci.cosw.springbootsecureapi.controller;


import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("api/task")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    List<Task> getAll() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @GetMapping("/{dueDate}")
    List<Task> getAllByDueDate(@PathVariable LocalDate dueDate) {
        return taskService.findTaskByDueDate(dueDate);
    }

    @GetMapping("/{status}")
    List<Task> getAllByStatus(@PathVariable String status) {
        return taskService.findTaskByStatus(status);
    }

    @PostMapping()
    Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }



}
