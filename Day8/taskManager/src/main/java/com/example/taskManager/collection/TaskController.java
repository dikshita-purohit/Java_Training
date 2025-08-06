package com.example.taskManager.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskManager.collection.repository.TaskRepository;
import com.example.taskManager.collection.task.Task;

@Service
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskRepository tr;

    @GetMapping
    public List<Task> getTasks() {
        return tr.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable("id") Long id) {
        return tr.findById(id);
    }

    @PostMapping
    public List<Task> addTask(@RequestBody Task task) {
        tr.save(task);
        return tr.findAll();
    }

    @PutMapping("/{id}")
    public List<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Optional<Task> existing = tr.findById(id);
        if (existing.isPresent()) {
            Task exist = existing.get();
            exist.setTaskName(task.getTaskName());
            exist.setTaskDesp(task.getTaskDesp());
            tr.save(exist);
        }
        return tr.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Task> deleteTask(@PathVariable("id") Long id) {
        tr.deleteById(id);
        return tr.findAll();
    }
}
