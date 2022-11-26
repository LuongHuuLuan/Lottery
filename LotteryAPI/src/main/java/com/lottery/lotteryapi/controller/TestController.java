package com.lottery.lotteryapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Test")
public class TestController {
    @GetMapping("")
    public List<String> getTest() {
        return List.of("Book", "Pen", "Rule");
    }
}
