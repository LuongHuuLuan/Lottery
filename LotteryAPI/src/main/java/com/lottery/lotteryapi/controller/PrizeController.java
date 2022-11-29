package com.lottery.lotteryapi.controller;

import com.lottery.lotteryapi.dto.PrizeDTO;
import com.lottery.lotteryapi.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/Prize")
public class PrizeController {
    @Autowired
    private PrizeService prizeService;
    @GetMapping
    public ResponseEntity<List<PrizeDTO>> getPrizes() {
        List<PrizeDTO> results = prizeService.getAllPrizes();
        if (results.size() != 0) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<PrizeDTO> getLotteryByDate(@PathVariable String code) {
        PrizeDTO result = prizeService.getPrizeByCode(code);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
