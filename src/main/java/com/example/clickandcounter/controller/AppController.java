package com.example.clickandcounter.controller;

import com.example.clickandcounter.service.api.NumberOfClicksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class AppController {
    @Autowired
    private NumberOfClicksService numberOfClicksService;


    @GetMapping("/index")
    public String index(Map<String, Object> model) {

        int counter = numberOfClicksService.getNumberOfClicks().getNumberOfClicks();
        model.put("counter", counter);
        return "index";
    }

    @PostMapping("/index")
    public synchronized void increaseNumber(){
        numberOfClicksService.atomicIncreaseNumber();
    }

    @MessageMapping("/ws")
    @SendTo("/topic")
    public String send(String string) throws Exception {
        return string;
    }


}
