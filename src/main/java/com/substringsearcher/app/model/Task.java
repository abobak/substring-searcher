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
public class Task {

    private UUID id;

    private String input;

    private String pattern;

    private Integer position;

    private Integer typos;

}
