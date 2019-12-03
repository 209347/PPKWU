package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StringAPIController {

    @GetMapping("/api/checkString/{message}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> checkString(@PathVariable String message) {
        List<Character> upperCaseLetters = new ArrayList<>();
        List<Character> lowerCaseLetters = new ArrayList<>();
        List<Character> digits = new ArrayList<>();
        List<Character> specialCharacters = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            char currentCharacter = message.charAt(i);
            if (Character.isUpperCase(currentCharacter)) {
                upperCaseLetters.add(currentCharacter);
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCaseLetters.add(currentCharacter);
            } else if (Character.isDigit(currentCharacter)) {
                digits.add(currentCharacter);
            } else {
                specialCharacters.add(currentCharacter);
            }
        }

        Map<String, Object> outputData = new HashMap<>();
        outputData.put("upperCaseLetters", upperCaseLetters);
        outputData.put("lowerCaseLetters", lowerCaseLetters);
        outputData.put("digits", digits);
        outputData.put("specialCharacters", specialCharacters);

        return outputData;
    }
}
