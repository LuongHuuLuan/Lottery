package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.PrizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPrizeRepository extends JpaRepository<PrizeEntity, Integer> {
    public List<PrizeEntity> findAll();
    public PrizeEntity findOneByCodePri(String codePri);
}
