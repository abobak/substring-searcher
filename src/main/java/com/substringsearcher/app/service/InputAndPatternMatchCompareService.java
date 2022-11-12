package com.substringsearcher.app.service;


import com.substringsearcher.app.dto.InputAndPatternCompareResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InputAndPatternMatchCompareService {


    Integer calculateDistance(String first, String second) {
        return LevenshteinDistance.getDefaultInstance().apply(first, second);
    }

    public InputAndPatternCompareResult calculatePatternAndInputDistance(String input, String pattern) {
        InputAndPatternCompareResult result = new InputAndPatternCompareResult(input.length(), pattern.length());
        int patternLastPossibleStartPosition = input.length() - pattern.length() + 1;
        for (int i = 0; i < patternLastPossibleStartPosition; i++) {
            Integer currentDistance = calculateDistance(input.substring(i, i + pattern.length()), pattern);
            if (currentDistance < result.getTypos()) {
                result.setTypos(currentDistance);
                result.setPosition(i);
            }
        }
        return result;
    }

}
