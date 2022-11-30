package com.lottery.lotteryapi.controller;

import com.lottery.lotteryapi.dto.DateDTO;
import com.lottery.lotteryapi.dto.LotteryDTO;
import com.lottery.lotteryapi.dto.PrizeDTO;
import com.lottery.lotteryapi.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Date")
public class DateController {
    @Autowired
    private DateService dateService;
    @GetMapping("/{provinceCode}")
    public ResponseEntity<List<DateDTO>> getLotteryByDate(@PathVariable String provinceCode) {
        List<DateDTO> results = dateService.getDateWithProvince(provinceCode);
        if (results.size() != 0) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
