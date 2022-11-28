package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.LotteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<LotteryEntity, Integer> {
}
