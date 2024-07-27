package com.khasanov.projectspring1.service;

import com.khasanov.projectspring1.dao.TaskDao;
import com.khasanov.projectspring1.dto.TaskRequestTo;
import com.khasanov.projectspring1.dto.TaskResponseTo;
import com.khasanov.projectspring1.entity.Task;
import com.khasanov.projectspring1.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNullElse;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskDao taskDao;
    private final TaskMapper taskMapper;

    public TaskResponseTo getTaskById(Integer id) {
        Task task = taskDao.findById(id).orElseThrow();
        return taskMapper.toTaskResponseTo(task);
    }

    public TaskResponseTo createTask(TaskRequestTo taskRequestTo) {
        Task task = taskMapper.toTask(taskRequestTo);
        Task taskInDb = taskDao.save(task);
        return taskMapper.toTaskResponseTo(taskInDb);
    }

    public TaskResponseTo updateTask(TaskRequestTo taskRequestTo) {
        Task task = taskMapper.toTask(taskRequestTo);
        task.setDescription(requireNonNullElse(taskRequestTo.getDescription(), task.getDescription()));
        task.setStatus(requireNonNullElse(taskRequestTo.getStatus(), task.getStatus()));
        Task taskInDb = taskDao.save(task);
        return taskMapper.toTaskResponseTo(taskInDb);
    }

    public void deleteTask(Integer id) {
        getTaskById(id);
        taskDao.deleteById(id);
    }
}
