package com.substringsearcher.app.service;


import com.substringsearcher.app.configuration.ForcedDelayConfiguration;
import com.substringsearcher.app.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
@Slf4j
public class InputAndPatternMatchCompareService {

    private final ForcedDelayConfiguration forcedDelayConfiguration;

    private final TaskCrudService taskCrudService;

    Integer calculateDistance(String first, String second) {
        return LevenshteinDistance.getDefaultInstance().apply(first, second);
    }


    public Task calculatePatternAndInputDistance(Task task) throws InterruptedException {
        int inputLength = task.getInput().length();
        int patternLength = task.getPattern().length();
        int patternLastPossibleStartPosition = inputLength - patternLength + 1;
        for (int i = 0; i < patternLastPossibleStartPosition; i++) {
            Integer currentDistance = calculateDistance(task.getInput().substring(i, i + patternLength), task.getPattern());
            if ((isNull(task.getTypos()) && (currentDistance < patternLength))
                    || (nonNull((task.getTypos())) && (currentDistance < task.getTypos()))
            ) {
                task.setTypos(currentDistance);
                task.setPosition(i);
            }
            taskCrudService.updateTaskStatus(task.getId(), false, i);
            log.info(
                    "Processing task with UUID: " + task.getId()
                            + ". Processed position: " + (i + 1) + " of " + patternLastPossibleStartPosition);
            Thread.sleep(forcedDelayConfiguration.getForcedDelay());
        }
        log.info("Finished processing task with UUID: " + task.getId());
        return task;
    }

    @Async("asyncExecutor")
    public void scheduleCalculation(Task task) throws InterruptedException {
        log.info("Registered new task with UUID: " + task.getId());
        calculatePatternAndInputDistance(task);
    }

}
