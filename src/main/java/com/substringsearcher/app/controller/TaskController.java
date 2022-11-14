package com.substringsearcher.app.controller;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.dto.TaskDto;
import com.substringsearcher.app.dto.TaskStatusDto;
import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.error.BadRequestException;
import com.substringsearcher.app.mapper.TaskMapper;
import com.substringsearcher.app.service.InputAndPatternMatchCompareService;
import com.substringsearcher.app.service.TaskCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskCrudService taskCrudService;

    private final TaskMapper taskMapper;

    private final InputAndPatternMatchCompareService inputAndPatternMatchCompareService;

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() {
        return taskMapper.dtosFromTaskList(taskCrudService.getTasks());
    }

    @GetMapping("/tasks/{id}")
    public TaskStatusDto getTaskStatus(@PathVariable UUID id) {
        return taskCrudService.getTaskStatus(id);
    }

    @PostMapping("/tasks")
    public UUID createTask(@RequestBody NewTaskPayloadDto newTaskPayloadDto) throws InterruptedException {
        throwExceptionIfInputOrPatternIsMissing(newTaskPayloadDto);
        throwExceptionIfPatternIsLongerThanInput(newTaskPayloadDto);
        Task newTask = taskCrudService.createNewTask(taskMapper.fromNewTaskDto(newTaskPayloadDto));
        inputAndPatternMatchCompareService.scheduleCalculation(newTask);
        return newTask.getId();
    }

    void throwExceptionIfInputOrPatternIsMissing(NewTaskPayloadDto newTaskPayloadDto) {
        if (isNull(newTaskPayloadDto.getInput()) || newTaskPayloadDto.getInput().isEmpty()) {
            throw new BadRequestException("Input can't be empty");
        } else if (isNull(newTaskPayloadDto.getPattern()) || newTaskPayloadDto.getPattern().isEmpty()) {
            throw new BadRequestException("Pattern can't be empty");
        }
    }

    void throwExceptionIfPatternIsLongerThanInput(NewTaskPayloadDto newTaskPayloadDto) {
        if (newTaskPayloadDto.getPattern().length() > newTaskPayloadDto.getInput().length()) {
            throw new BadRequestException("Pattern can't be longer than input");
        }
    }

}
