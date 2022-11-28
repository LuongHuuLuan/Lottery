package com.lottery.lotteryapi.controller;

import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Lottery")
public class TestController {
    @Autowired
    private LotteryService lotteryService;

    @GetMapping("")
    public List<LotteryEntity> getTest() {
        return lotteryService.getAllLottery();
    }
}
