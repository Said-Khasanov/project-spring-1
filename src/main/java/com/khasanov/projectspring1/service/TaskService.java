package com.khasanov.projectspring1.service;

import com.khasanov.projectspring1.dao.TaskDao;
import com.khasanov.projectspring1.dto.TaskRequestTo;
import com.khasanov.projectspring1.dto.TaskResponseTo;
import com.khasanov.projectspring1.entity.Task;
import com.khasanov.projectspring1.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNullElse;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskDao taskDao;
    private final TaskMapper taskMapper;

    public Page<TaskResponseTo> getTasks(Integer page, Integer size) {
        page = Math.max(page - 1, 0);
        return taskDao.findAll(PageRequest.of(page, size)).map(taskMapper::toTaskResponseTo);
    }

    private Task getTaskById(Integer id) {
        return taskDao.findById(id).orElseThrow();
    }

    public void createTask(TaskRequestTo taskRequestTo) {
        Task task = taskMapper.toTask(taskRequestTo);
        Task taskInDb = taskDao.save(task);
        taskMapper.toTaskResponseTo(taskInDb);
    }

    public void updateTask(TaskRequestTo taskRequestTo) {
        Task task = getTaskById(taskRequestTo.getId());
        task.setDescription(requireNonNullElse(taskRequestTo.getDescription(), task.getDescription()));
        task.setStatus(requireNonNullElse(taskRequestTo.getStatus(), task.getStatus()));
        Task taskInDb = taskDao.save(task);
        taskMapper.toTaskResponseTo(taskInDb);
    }

    public void deleteTask(Integer id) {
        getTaskById(id);
        taskDao.deleteById(id);
    }
}
