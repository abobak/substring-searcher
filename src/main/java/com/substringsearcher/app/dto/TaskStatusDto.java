package com.substringsearcher.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class TaskStatusDto {

    private UUID taskId;

    private String progress;

}
