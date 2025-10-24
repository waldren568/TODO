package com.company.TODO.List.Sevice;

import com.company.TODO.List.Model.Task;
import com.company.TODO.List.Repository.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService{

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks(){

        return taskRepository.findAll();
    }

    public TaskService(TaskRepository taskRepository){

        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task){

        return taskRepository.save(task);
    }

    public void deleteByID(Long id){

        taskRepository.deleteById(id);
    }

    public Task findById(Long id){

        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }

    public Task createTask(@NotNull Task task){

        if(taskRepository.existsByTitle(task.getTitle())){

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A task with the same title has already been created"
            );
        }

        task.setCompleted(false);
        return taskRepository.save(task);
    }
}
