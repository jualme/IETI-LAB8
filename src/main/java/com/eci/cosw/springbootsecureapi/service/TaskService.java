package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    Task getTask( Long id );

    Task createTask( Task task );

    List<Task> findTaskByDueDate( LocalDate dueDate );

    List<Task> findTaskByStatus( String status);
}
