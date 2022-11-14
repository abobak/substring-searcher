package com.substringsearcher.app.service;

import com.substringsearcher.app.dto.TaskStatusDto;
import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.model.TaskStatus;
import com.substringsearcher.app.repository.TaskRepository;
import com.substringsearcher.app.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskCrudService {


    private final TaskStatusRepository taskStatusRepository;

    private final TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        taskRepository.saveTask(task);
        taskStatusRepository.createTaskStatus(task);
        return task;
    }

    public TaskStatusDto getTaskStatus(UUID taskId) {
        TaskStatus t = taskStatusRepository.getTaskStatus(taskId);
        TaskStatusDto dto = new TaskStatusDto(taskId, stepsToPercentageString(t.getCurrentStep(), t.getMaxSteps()));
        return dto;
    }

    public void updateTaskStatus(UUID taskId, boolean completed, Integer currentStep) {
        taskStatusRepository.updateTaskStatus(taskId, completed, currentStep);
    }

    public List<Task> getTasks() {
        return taskRepository.getAllTasks();
    }

    static String stepsToPercentageString(Integer currentStep, Integer maxSteps) {
        Double progress = (Double.valueOf(currentStep) / Double.valueOf(maxSteps)) * 100;
        return String.format("%.2f%%", progress);
    }
}
