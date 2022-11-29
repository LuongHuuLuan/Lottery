package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.LotteryDTO;
import com.lottery.lotteryapi.dto.ResultDTO;
import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ResultEntity;
import com.lottery.lotteryapi.repository.IDateRepository;
import com.lottery.lotteryapi.repository.ILotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryService {

    @Autowired
    private ILotteryRepository lotteryRepository;
    @Autowired
    private IDateRepository dateRepository;


    public List<LotteryDTO> getLotteriesToday() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        return getLotteries(day + "-" + month + "-" + year);
    }

    public List<LotteryDTO> getLotteries(String date) {
        List<LotteryDTO> results = new ArrayList<>();
        DateEntity dateEntity = dateRepository.findOneByShortDate(date.replaceAll("-", "/"));
        if (dateEntity != null) {
            List<LotteryEntity> lotteryEntities = lotteryRepository.findByDateAndIsDelete(dateEntity, "FALSE");
            for (LotteryEntity lotteryEntity : lotteryEntities) {
                LotteryDTO lotteryDTO = new LotteryDTO();
                lotteryDTO.setId(lotteryEntity.getIdLot());
                lotteryDTO.setDate(lotteryEntity.getDate().getFullDate());
                lotteryDTO.setProvince(lotteryEntity.getProvince().getName());
                List<ResultDTO> lotteryResults = new ArrayList<>();
                for (ResultEntity resultEntity : lotteryEntity.getResults()) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setPrizeName(resultEntity.getPrize().getName());
                    resultDTO.setResults(resultEntity.getResult());
                    lotteryResults.add(resultDTO);
                }

                results.add(lotteryDTO);
                lotteryDTO.setResults(lotteryResults);
            }
        }
        return results;
    }
}
