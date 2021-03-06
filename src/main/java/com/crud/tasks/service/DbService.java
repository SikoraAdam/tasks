package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task saveTask(final Task task) {
        log.info("saveTask {}", task);
        return repository.save(task);
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public boolean taskExist (final Long id) {
            return repository.findById(id).isPresent();
    }

    public void deleteTask(final Long id) {
        repository.delete(id);
    }
}

