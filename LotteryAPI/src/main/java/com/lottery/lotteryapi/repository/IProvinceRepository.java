package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvinceRepository extends JpaRepository<ProvinceEntity, Integer> {
    public ProvinceEntity findOneByName(String name);
    public ProvinceEntity findOneByIdPro(int id);

    public ProvinceEntity findOneByCodePro(String code);

}
