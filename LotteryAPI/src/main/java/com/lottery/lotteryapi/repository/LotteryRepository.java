package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.LotteryEntity;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LotteryRepository extends JpaRepository<LotteryEntity, Integer> {

}
