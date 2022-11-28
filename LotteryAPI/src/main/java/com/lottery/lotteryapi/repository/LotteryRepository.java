package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LotteryRepository extends JpaRepository<LotteryEntity, Integer> {
    public List<LotteryEntity> findByProvince_IdPro(int idPro);
    public List<LotteryEntity> findByDateAndIsDelete(DateEntity date, String isDelete);
}
