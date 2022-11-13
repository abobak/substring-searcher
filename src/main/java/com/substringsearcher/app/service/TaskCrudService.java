package com.substringsearcher.app.service;

import com.substringsearcher.app.dto.TaskStatusDto;
import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.model.TaskStatus;
import com.substringsearcher.app.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskCrudService {

    private final InputAndPatternMatchCompareService inputAndPatternMatchCompareService;

    private final TaskStatusRepository taskStatusRepository;

    public Task createNewTask(Task task) {
        taskStatusRepository.createTaskStatus(task);
        return task;
    }

    public TaskStatusDto getTaskStatus(UUID taskId) {
        TaskStatus t = taskStatusRepository.getTaskStatus(taskId);
        TaskStatusDto dto = new TaskStatusDto(taskId, stepsToPercentageString(t.getCurrentStep(), t.getMaxSteps()));
        return dto;
    }

    public List<Task> getTasks() {
        return new LinkedList<>();
    }

    static String stepsToPercentageString(Integer curentStep, Integer maxSteps) {
        Double progress = (Double.valueOf(curentStep) / Double.valueOf(maxSteps)) * 100;
        return String.format("%.2f%%", progress);
    }
}
