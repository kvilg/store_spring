package com.example.demo.servis;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CodeGeneration {

    public String generate(){
        StringBuilder codeSr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            codeSr.append(ThreadLocalRandom.current().nextInt(0,10));
        }
        return codeSr.toString();
    }

}
