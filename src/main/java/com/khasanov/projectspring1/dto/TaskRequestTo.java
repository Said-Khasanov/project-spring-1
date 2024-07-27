package com.khasanov.projectspring1.dto;

import com.khasanov.projectspring1.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskRequestTo {
    private Integer id;
    private String description;
    private Status status;
}
