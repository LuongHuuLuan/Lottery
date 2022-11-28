package com.lottery.lotteryapi.controller;

import com.lottery.lotteryapi.dto.LotteryDTO;
import com.lottery.lotteryapi.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Lottery")
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    @GetMapping
    public ResponseEntity<List<LotteryDTO>> getLotteriesToday() {
        List<LotteryDTO> results = lotteryService.getLotteriesToday();
        if (results.size() != 0) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<LotteryDTO>> getLotteryByDate(@PathVariable String date) {
        List<LotteryDTO> results = lotteryService.getLotteries(date);
        if (results.size() != 0) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
