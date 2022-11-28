package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {

    @Autowired
    private LotteryRepository lotteryRepository;

    public List<LotteryEntity> getAllLottery() {
        return lotteryRepository.findAll();
    }

    public Optional<LotteryEntity> getLottery(Integer id) {
        return lotteryRepository.findById(id);
    }
}
