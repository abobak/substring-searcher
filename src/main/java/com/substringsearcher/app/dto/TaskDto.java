package com.substringsearcher.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class TaskDto {

    private UUID id;

    private String input;

    private String pattern;

    private Integer position;

    private Integer typos;

}
