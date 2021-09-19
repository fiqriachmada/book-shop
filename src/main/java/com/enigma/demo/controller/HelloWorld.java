package com.enigma.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class HelloWorld {

    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    // Path variable
    // Get data buku by id

    @GetMapping("/hello/{variable}")
    public String pathVariable(@PathVariable String variable) {
        return "Path Variable " + variable;
    }

    // For pagination, searching
    @GetMapping("/req-query")
    public String queryString(@RequestParam String var) {
        return "Request Param = " + var;
    }

    @PostMapping("/hello-body")
    public String reqBody(@RequestBody HashMap<String, String> mapBody) {
        return "Request Body = " + mapBody;
    }
}
