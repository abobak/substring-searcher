package com.substringsearcher.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InputAndPatternCompareResult {

    private Integer position;

    private Integer typos;

}
