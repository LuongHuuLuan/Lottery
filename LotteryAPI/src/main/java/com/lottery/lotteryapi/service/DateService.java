package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.DateDTO;
import com.lottery.lotteryapi.dto.LotteryDTO;
import com.lottery.lotteryapi.dto.ResultDTO;
import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ProvinceEntity;
import com.lottery.lotteryapi.entity.ResultEntity;
import com.lottery.lotteryapi.repository.ILotteryRepository;
import com.lottery.lotteryapi.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DateService {

    @Autowired
    private IProvinceRepository provinceRepository;
    @Autowired
    private ILotteryRepository lotteryRepository;

    public List<DateDTO> getDateWithProvince(String provinceCode) {
        List<DateDTO> results = new ArrayList<>();
        ProvinceEntity provinceEntity = provinceRepository.findOneByCodePro(provinceCode);
        List<LotteryEntity> lotteryEntities = lotteryRepository.findFirst4ByProvinceAndIsDeleteOrderByIdLotDesc(provinceEntity, "FALSE");
        for (LotteryEntity lotteryEntity : lotteryEntities) {
            DateEntity dateEntity = lotteryEntity.getDate();
            DateDTO dateDTO = new DateDTO();
            dateDTO.setId(dateEntity.getIdDate());
            dateDTO.setFullDate(dateEntity.getFullDate());
            dateDTO.setShortDate(dateEntity.getShortDate());
            dateDTO.setDay(dateEntity.getDay());
            dateDTO.setDate(dateEntity.getDate());
            dateDTO.setMonth(dateEntity.getMonth());
            dateDTO.setYear(dateEntity.getYear());
            results.add(dateDTO);
        }
        return results;
    }
}
