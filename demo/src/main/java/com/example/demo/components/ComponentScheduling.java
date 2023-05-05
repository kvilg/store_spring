package com.example.demo.components;

import com.example.demo.servis.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ComponentScheduling {

    @Autowired
    private JwtService service;


    @Scheduled(cron = "0 0 12 * * *")
    @Async
    public void scheduledRewoveOldToken() throws ParseException {
        service.rewoveOldDate();
    }

}
