package com.substringsearcher.app.repository;

import com.substringsearcher.app.error.BadRequestException;
import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.model.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@Slf4j
public class TaskStatusRepository {

    private static final Map<UUID, TaskStatus> taskStatusDatabase = new HashMap<>();

    public void createTaskStatus(Task task) {
        int maxSteps = task.getInput().length() - task.getPattern().length();
        TaskStatus status = new TaskStatus(task.getId(), false, maxSteps, 0);
        taskStatusDatabase.put(task.getId(), status);
    }

    public TaskStatus getTaskStatus(UUID id) {
        return taskStatusDatabase.getOrDefault(id, null);
    }

    public void updateTaskStatus(UUID id, boolean completed, int currentStep) {
        if (!taskStatusDatabase.containsKey(id)) {
            log.error("Attempt to update state of non-existent task with id " + id);
        } else {
            TaskStatus forUpdate = taskStatusDatabase.get(id);
            forUpdate.setCompleted(completed);
            forUpdate.setCurrentStep(currentStep);
        }
    }

}
