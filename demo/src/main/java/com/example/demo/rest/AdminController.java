package com.example.demo.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    @GetMapping("/admin")
    public String admin(){
        return "U ADMIN";
    }
}
