package com.substringsearcher.app.service;


import com.substringsearcher.app.configuration.ForcedDelayConfiguration;
import com.substringsearcher.app.model.Task;
import com.substringsearcher.app.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
@Slf4j
public class InputAndPatternMatchCompareService {

    private final ForcedDelayConfiguration forcedDelayConfiguration;

    Integer calculateDistance(String first, String second) {
        return LevenshteinDistance.getDefaultInstance().apply(first, second);
    }

    public Task calculatePatternAndInputDistance(Task task) throws InterruptedException {
        int inputLength = task.getInput().length();
        int patternLength = task.getPattern().length();
        TaskStatus status = new TaskStatus(task.getId(), false, inputLength, 0);

        int patternLastPossibleStartPosition = task.getInput().length() - task.getPattern().length() + 1;
        for (int i = 0; i < patternLastPossibleStartPosition; i++) {
            Integer currentDistance = calculateDistance(task.getInput().substring(i, i + patternLength), task.getPattern());
            if ((isNull(task.getTypos()) && (currentDistance < patternLength))
                    || (nonNull((task.getTypos())) && (currentDistance < task.getTypos()))
            ) {
                task.setTypos(currentDistance);
                task.setPosition(i);
            }
            Thread.sleep(forcedDelayConfiguration.getForcedDelay());
        }
        return task;
    }

}
