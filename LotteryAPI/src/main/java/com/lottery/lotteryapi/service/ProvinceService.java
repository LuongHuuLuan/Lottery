package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.ProvinceDTO;
import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ProvinceEntity;
import com.lottery.lotteryapi.repository.IDateRepository;
import com.lottery.lotteryapi.repository.ILotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private IDateRepository dateRepository;
    @Autowired
    private ILotteryRepository lotteryRepository;

    public List<ProvinceDTO> getProvincesToday() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        return getProvinces(day + "-" + month + "-" + year);
    }

    public List<ProvinceDTO> getProvinces(String date) {
        List<ProvinceDTO> results = new ArrayList<>();
        DateEntity dateEntity = dateRepository.findOneByShortDate(date.replaceAll("-", "/"));
        if (dateEntity != null) {
            List<LotteryEntity> lotteryEntities = lotteryRepository.findByDateAndIsDelete(dateEntity, "FALSE");
            for (LotteryEntity lotteryEntity : lotteryEntities) {
                ProvinceEntity provinceEntity = lotteryEntity.getProvince();
                ProvinceDTO provinceDTO = new ProvinceDTO();
                provinceDTO.setId(provinceEntity.getIdPro());
                provinceDTO.setCode(provinceEntity.getCodePro());
                provinceDTO.setName(provinceEntity.getName());
                results.add(provinceDTO);
            }
        }
        return results;
    }
}
