package com.substringsearcher.app.service;

import com.substringsearcher.app.configuration.ForcedDelayConfiguration;
import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.model.TaskStatus;
import com.substringsearcher.app.repository.TaskRepository;
import com.substringsearcher.app.repository.TaskStatusRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputAndPatternMatchCompareServiceTest {

    InputAndPatternMatchCompareService sut = new InputAndPatternMatchCompareService(
            new ForcedDelayConfiguration(0), new TaskCrudService(new TaskStatusRepository(), new TaskRepository()));

    @ParameterizedTest
    @MethodSource("inputsAndResults")
    void shouldCalculateBestPatternFit(
            String input, String pattern, Integer expectedPosition, Integer expectedTypos) throws Exception {
        Task result = sut.calculatePatternAndInputDistance(new Task(UUID.randomUUID(), input, pattern, null, null));
        assertEquals(expectedPosition, result.getPosition());
        assertEquals(expectedTypos, result.getTypos());
    }

    static Stream<Arguments> inputsAndResults() {
        return Stream.of(
                Arguments.of("ABCD", "BCD", 1, 0),
                Arguments.of("ABCD", "BWD", 1, 1),
                Arguments.of("ABCDEFG", "CFG", 4, 1),
                Arguments.of("ABCABC", "ABC", 0, 0),
                Arguments.of("ABCDEFG", "TDD", 1, 2)
        );
    }


}
