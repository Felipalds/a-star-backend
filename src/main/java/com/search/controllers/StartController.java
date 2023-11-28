package com.search.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StartController {

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> start(@RequestBody Map<String, Object> payload) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
