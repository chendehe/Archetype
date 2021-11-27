package com.chendehe.controller;

import com.chendehe.service.impl.RocketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rocket")
public class RocketController {

    @Autowired
    private RocketServiceImpl rocketService;

    @GetMapping("/send/{type}/{msg}")
    public String send(@PathVariable int type, @PathVariable String msg) {

        log.info("send start");

        if (type == 1) {
            rocketService.sendMessageAsync(msg);
        } else {
            rocketService.sendMessage(msg);
        }

        log.info("send end");
        return "OK";
    }


}
