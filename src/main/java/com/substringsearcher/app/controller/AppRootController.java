package com.substringsearcher.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRootController {

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

}
