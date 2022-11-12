package com.substringsearcher.app.controller;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.dto.TaskDto;
import com.substringsearcher.app.service.InputAndPatternMatchCompareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final InputAndPatternMatchCompareService inputAndPatternMatchCompareService;

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
        return UUID.randomUUID();
    }

}
