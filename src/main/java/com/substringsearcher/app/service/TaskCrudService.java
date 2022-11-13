package com.substringsearcher.app.service;

import com.substringsearcher.app.dto.TaskStatusDto;
import com.substringsearcher.app.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskCrudService {

    private final InputAndPatternMatchCompareService inputAndPatternMatchCompareService;

    public Task createNewTask(Task task) {
        return task;
    }

    public TaskStatusDto getTaskStatus(UUID taskId) {
        return null;
    }

    public List<Task> getTasks() {
        return new LinkedList<>();
    }

}
