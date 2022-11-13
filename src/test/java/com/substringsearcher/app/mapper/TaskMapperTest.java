package com.substringsearcher.app.mapper;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.dto.TaskDto;
import com.substringsearcher.app.model.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskMapperTest {

    private static TaskMapper mapper;

    @BeforeAll
    static void setUp() {
        mapper = new TaskMapperImpl();
    }

    @Test
    void newlyCreatedTaskShouldHaveIdPopulated() {
        NewTaskPayloadDto dto = new NewTaskPayloadDto("ABCD", "ABC");
        Task newTask = mapper.fromNewTaskDto(dto);
        assertNotNull(newTask.getId());
    }

    @Test
    void taskMappedFromNewTaskPayloadDtoShouldHaveInputAndPatternCopied() {
        String expectedInput = "EXPECTED_INPUT";
        String expectedPattern = "PATTERN";
        NewTaskPayloadDto dto = new NewTaskPayloadDto(expectedInput, expectedPattern);
        Task newTask = mapper.fromNewTaskDto(dto);
        assertEquals(expectedInput, newTask.getInput());
        assertEquals(expectedPattern, newTask.getPattern());
    }

    @Test
    void listOfTasksShouldBeConvertedToListOfTaskDtos() {
        // given
        UUID expectedId = UUID.fromString("72b74d2c-2193-4e43-821b-d2eaff728876");
        String expectedInput = "EXPECTED_INPUT";
        String expectedPattern = "PATTERN";
        Task t = new Task(expectedId, expectedInput, expectedPattern, null, null);
        List<Task> tasks = List.of(t);

        // when
        List<TaskDto> taskDtos = mapper.dtosFromTaskList(tasks);

        // then
        assertEquals(1, taskDtos.size());
        TaskDto dto = taskDtos.get(0);
        assertEquals(expectedId, dto.getId());
        assertEquals(expectedInput, dto.getInput());
        assertEquals(expectedPattern, dto.getPattern());

    }

}
