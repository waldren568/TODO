package com.company.TODO.List.Controller;

import org.springframework.ui.Model;
import com.company.TODO.List.Model.Task;
import com.company.TODO.List.Sevice.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/index")
public class TaskController{

    private final TaskService taskService;

    public TaskController(TaskService taskService){

        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model){

        List<Task> tasksList = taskService.getAllTasks();
        model.addAttribute("tasks", tasksList);
        return "index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model){

        model.addAttribute("task", new Task());
        return "new";
    }

    @PostMapping("/new")
    public String saveNewTask(@ModelAttribute Task task,
                              BindingResult br){

        if (br.hasErrors()) return "new";
        task.setCompleted(false);
        taskService.saveTask(task);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){

        taskService.deleteByID(id);
        return "redirect:/index";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Long id){

        Task t = taskService.findById(id);
        t.setCompleted(!t.isCompleted());
        taskService.saveTask(t);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute Task task){

        taskService.saveTask(task);
        return "redirect:/index";
    }
}
