package com.substringsearcher.app.mapper;

import com.substringsearcher.app.dto.NewTaskPayloadDto;
import com.substringsearcher.app.entity.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

}
