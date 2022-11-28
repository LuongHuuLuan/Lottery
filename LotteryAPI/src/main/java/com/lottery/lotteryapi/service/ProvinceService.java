package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.ProvinceDTO;
import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ProvinceEntity;
import com.lottery.lotteryapi.repository.DateRepository;
import com.lottery.lotteryapi.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private DateRepository dateRepository;
    @Autowired
    private LotteryRepository lotteryRepository;

    public List<ProvinceDTO> getProvincesToday() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        return getProvinces(day + "-" + month + "-" + year);
    }

    public List<ProvinceDTO> getProvinces(String date) {
        int day;
        int month;
        int year;
        List<ProvinceDTO> results = new ArrayList<>();
        try {
            day = Integer.parseInt(date.split("-")[0]);
            month = Integer.parseInt(date.split("-")[1]);
            year = Integer.parseInt(date.split("-")[2]);
            DateEntity dateEntity = dateRepository.findOneByDateAndMonthAndYear(day, month, year);
            List<LotteryEntity> lotteryEntities = lotteryRepository.findByDateAndIsDelete(dateEntity, "FALSE");
            for (LotteryEntity lotteryEntity : lotteryEntities) {
                ProvinceEntity provinceEntity = lotteryEntity.getProvince();
                ProvinceDTO provinceDTO = new ProvinceDTO();
                provinceDTO.setIdPro(provinceEntity.getIdPro());
                provinceDTO.setName(provinceEntity.getName());
                results.add(provinceDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
