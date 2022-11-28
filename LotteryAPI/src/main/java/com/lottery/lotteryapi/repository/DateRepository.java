package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.DateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<DateEntity, Integer> {
}
