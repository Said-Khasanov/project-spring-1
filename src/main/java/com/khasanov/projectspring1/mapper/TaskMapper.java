package com.khasanov.projectspring1.mapper;

import com.khasanov.projectspring1.dto.TaskRequestTo;
import com.khasanov.projectspring1.dto.TaskResponseTo;
import com.khasanov.projectspring1.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseTo toTaskResponseTo(Task task);
    Task toTask(TaskRequestTo taskRequestTo);
}
