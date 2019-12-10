package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;

@RestController
public class CalendarController {

    @GetMapping("/api/getCurrentMonth") @ResponseStatus(HttpStatus.OK) public ResponseEntity<String> getCurrentMonth() {
        return new ResponseEntity<String>;
    }

    @GetMapping("/api/getCalendarMonth/{month}") @ResponseStatus(HttpStatus.OK) public ResponseEntity<String> getCurrentMonth(@PathVariable String month) {
        return new ResponseEntity<String>;
    }

}
