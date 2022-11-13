package com.substringsearcher.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskStatus {

    private UUID taskId;

    private boolean completed;

    private Integer maxSteps;

    private Integer currentStep;

}
