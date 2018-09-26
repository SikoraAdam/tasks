package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /*
    #Module 15.2#
    public Task getTaskById(Long id) {
        Optional<Task> matchingTask = repository.findById(id).stream().findAny();
        return matchingTask.orElse(null);
    }*/

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteTask(final Long id) {
        repository.deleteById(id);
    }
}
