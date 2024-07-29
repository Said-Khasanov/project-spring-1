package com.khasanov.projectspring1.controller;

import com.khasanov.projectspring1.dto.TaskResponseTo;
import com.khasanov.projectspring1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public String getTasks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Model model
    ) {
        Page<TaskResponseTo> tasks = taskService.getTasks(PageRequest.of(page - 1, size));
        int totalPages = tasks.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("tasks", tasks.toList());
        return "tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Model model
    ) {
        taskService.deleteTask(id);
        return getTasks(page, size, model);
    }
}
