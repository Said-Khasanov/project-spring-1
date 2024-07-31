package com.khasanov.projectspring1.controller;

import com.khasanov.projectspring1.dto.TaskRequestTo;
import com.khasanov.projectspring1.dto.TaskResponseTo;
import com.khasanov.projectspring1.entity.Status;
import com.khasanov.projectspring1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping({"", "/"})
public class TaskController {
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_PAGE_NUMBER = "1";
    private final TaskService taskService;

    @GetMapping
    public String getTasks(
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            Model model
    ) {
        Page<TaskResponseTo> tasks = taskService.getTasks(page, size);
        model.addAttribute("totalPages", tasks.getTotalPages());
        model.addAttribute("tasks", tasks.toList());
        model.addAttribute("statuses", Status.values());
        return "tasks";
    }

    @PostMapping
    public String createTask(
            @RequestBody TaskRequestTo taskRequestTo,
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            Model model
    ) {
        taskService.createTask(taskRequestTo);
        return getTasks(page, size, model);
    }

    @PutMapping
    public String updateTask(
            @RequestBody TaskRequestTo taskRequestTo,
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            Model model
    ) {
        taskService.updateTask(taskRequestTo);
        return getTasks(page, size, model);
    }

    @DeleteMapping("/{id:\\d+}")
    public String deleteTask(
            @PathVariable Integer id,
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            Model model
    ) {
        taskService.deleteTask(id);
        return getTasks(page, size, model);
    }
}
