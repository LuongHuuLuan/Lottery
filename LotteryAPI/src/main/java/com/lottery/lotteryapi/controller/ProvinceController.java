package com.lottery.lotteryapi.controller;

import com.lottery.lotteryapi.dto.ProvinceDTO;
import com.lottery.lotteryapi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<ProvinceDTO>> getProvinceToday() {
        List<ProvinceDTO> results = provinceService.getProvincesToday();
        if (results.size() != 0) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<ProvinceDTO>> getProvinceToday(@PathVariable String date) {
        List<ProvinceDTO> results = provinceService.getProvinces(date);
        if (results.size() != 0) {
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
