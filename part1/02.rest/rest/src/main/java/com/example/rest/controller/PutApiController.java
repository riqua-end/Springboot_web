package com.example.rest.controller;

import com.example.rest.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class PutApiController {

    @PutMapping("/put")
    public void put(@RequestBody UserRequest userRequest){
        log.info("Request : {}", userRequest);
    }
}
