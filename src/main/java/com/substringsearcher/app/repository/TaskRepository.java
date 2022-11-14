package com.substringsearcher.app.repository;

import com.substringsearcher.app.model.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TaskRepository {

    private static final Map<UUID, Task> taskDatabase = new HashMap<>();

    public void saveTask(Task t) {
        taskDatabase.put(t.getId(), t);
    }

    public List<Task> getAllTasks() {
        return taskDatabase.values().stream().toList();
    }
}
