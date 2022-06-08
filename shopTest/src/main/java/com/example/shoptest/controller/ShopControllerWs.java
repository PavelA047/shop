package com.example.shoptest.controller;

import com.example.shoptest.dto.Greeting;
import com.example.shoptest.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ShopControllerWs {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(500);
        return new Greeting(message.getName() + " добавлен в коризну!");
    }
}
