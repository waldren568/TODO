package com.company.TODO.List.Controller;

import com.company.TODO.List.Model.Task;
import com.company.TODO.List.Repository.TaskRepository;
import com.company.TODO.List.Sevice.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskAPIController{

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> all(){

        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task){

        task.setId(id);
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){

        taskService.deleteByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody @NotNull Task task){

        task.setId(null);
        return taskService.createTask(task);
    }

    @GetMapping("/exists")
    public boolean existsByTitle(@RequestParam String title){

        return taskRepository.existsByTitle(title);
    }
}
