package com.example.taskManager.collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskManager.collection.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
