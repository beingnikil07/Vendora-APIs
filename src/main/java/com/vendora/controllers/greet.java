package com.vendora.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class greet {
    @GetMapping("test-error")
    public String greet() {
        throw new RuntimeException("Test my springboot global excception handler");
    }
}
