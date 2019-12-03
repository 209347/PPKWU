package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReverseController {

    @GetMapping("/api/reverse/{message}") @ResponseStatus(HttpStatus.OK) public String reverseMessage(@PathVariable String message) {
        return new StringBuilder(message).reverse().toString();
    }

}