package com.eci.cosw.springbootsecureapi.service;

import com.eci.cosw.springbootsecureapi.model.Task;
import com.eci.cosw.springbootsecureapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceimpl implements TaskService {

  private List<Task> tasks = new ArrayList<>();

  @Autowired
  public TaskServiceimpl() {}

  @PostConstruct
  private void populateSampleData() {
    tasks.add(
        Task.builder()
            .id(1L)
            .description("Test task")
            .dueDate(LocalDate.of(2020, 1, 10))
                .responsible(new User("test@mail.com","password","Test","Ieti"))
            .status("Pending")
            .build());
  }

  @Override
  public List<Task> getTasks() {
    return tasks;
  }

  @Override
  public Task getTask(Long id) {
    Task taskAns = null;
    for (Task task : tasks ) {
        if (task.getId().equals(id)){
          taskAns = task;
        }
    }
    return taskAns;
  }

  @Override
  public Task createTask(Task task) {
    tasks.add(task);
    return task;
  }

  @Override
  public List<Task> findTaskByDueDate(LocalDate dueDate) {
    List<Task> taskAns = new ArrayList<>();
    for (Task task : tasks ) {
      if (task.getDueDate().equals(dueDate)){
        taskAns.add(task);
      }
    }
    return taskAns;
  }

  @Override
  public List<Task> findTaskByStatus(String status) {
    List<Task> taskAns = new ArrayList<>();
    for (Task task : tasks ) {
      if (task.getStatus().equals(status)){
        taskAns.add(task);
      }
    }
    return taskAns;
  }
}
