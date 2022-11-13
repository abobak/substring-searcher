package com.substringsearcher.app.controller;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.dto.TaskDto;
import com.substringsearcher.app.entity.Task;
import com.substringsearcher.app.error.BadRequestException;
import com.substringsearcher.app.mapper.TaskMapper;
import com.substringsearcher.app.service.InputAndPatternMatchCompareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final InputAndPatternMatchCompareService inputAndPatternMatchCompareService;

    private final TaskMapper taskMapper;

    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks() throws InterruptedException {
        return new ArrayList<>();
    }

    @GetMapping("/tasks/{id}")
    public TaskDto getTask(@PathVariable UUID id) {
        return new TaskDto(id, "", "", 0, 0);
    }

    @PostMapping
    public UUID createTask(NewTaskPayloadDto newTaskPayloadDto) {
        throwExceptionIfInputOrPatternIsMissing(newTaskPayloadDto);
        throwExceptionIfPatternIsLongerThanInput(newTaskPayloadDto);
        Task newTask = taskMapper.fromNewTaskDto(newTaskPayloadDto);
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
