package com.mm.example.controller;

import com.mm.example.service.ApiFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/data")
public class DataProcessController {

    private final ApiFetchService service;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getAll(){
         service.synAll();
        return "Successfully got data from API";
    }
}
