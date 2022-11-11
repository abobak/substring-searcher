package com.substringsearcher.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewTaskPayloadDto {

    private String input;

    private String pattern;

}
