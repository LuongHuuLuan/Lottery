package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Integer> {
    public ProvinceEntity findOneByName(String name);
    public ProvinceEntity findOneByIdPro(int id);
}
