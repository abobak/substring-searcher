package com.substringsearcher.app.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskCrudServiceTest {



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
}
