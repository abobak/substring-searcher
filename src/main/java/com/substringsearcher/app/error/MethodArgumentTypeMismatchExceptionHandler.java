package com.substringsearcher.app.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MethodArgumentTypeMismatchExceptionHandler {

    @AllArgsConstructor
    @Getter
    class Error {
        private String message;
    }

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public Error handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex) {
        return new Error("Incorrect input provided. " + ex.getCause().getMessage());
    }


}
