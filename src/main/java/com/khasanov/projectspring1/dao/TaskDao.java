package com.khasanov.projectspring1.dao;

import com.khasanov.projectspring1.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Integer> {

}
