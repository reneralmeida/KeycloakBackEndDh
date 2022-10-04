package com.digitalhouse.msgateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class test {

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("test");
    }
}
