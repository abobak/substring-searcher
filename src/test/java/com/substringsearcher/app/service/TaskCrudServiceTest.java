package com.substringsearcher.app.service;

import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.repository.TaskRepository;
import com.substringsearcher.app.repository.TaskStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskCrudServiceTest {

    TaskCrudService serviceUnderTest;

    @BeforeEach
    void setUp() {
        this.serviceUnderTest = new TaskCrudService(new TaskStatusRepository(), new TaskRepository());
    }

    @ParameterizedTest
    @MethodSource("inputsAndMessage")
    void shouldCorrectlyGeneratePercentageMessage(Integer first, Integer second, String expectedString) {
        String result = TaskCrudService.stepsToPercentageString(first, second);
        assertEquals(expectedString, result);
    }

    static Stream<Arguments> inputsAndMessage() {
        return Stream.of(
                Arguments.of(1, 2, "50,00%"),
                Arguments.of(2, 3, "66,67%"),
                Arguments.of(3, 3, "100,00%")
        );
    }

    @Test
    void creationOfNewTaskShouldResultWithCreationOfCorrespondingTaskStatus() {
        // given
        UUID expectedId = UUID.randomUUID();
        Task t = new Task(expectedId, "ANYTHING", "ANYTHING", null, null);

        // when
        serviceUnderTest.createNewTask(t);

        // then
        assertNotNull(serviceUnderTest.getTaskStatus(expectedId));

    }
}
