package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILotteryRepository extends JpaRepository<LotteryEntity, Integer> {
    public List<LotteryEntity> findByProvince_IdPro(int idPro);
    public List<LotteryEntity> findByDateAndIsDelete(DateEntity date, String isDelete);

    public List<LotteryEntity> findFirst4ByProvinceAndIsDeleteOrderByIdLotDesc(ProvinceEntity province, String isDelete);

    public LotteryEntity findOneByProvinceAndDateAndIsDelete(ProvinceEntity province, DateEntity date, String isDelete);
}
