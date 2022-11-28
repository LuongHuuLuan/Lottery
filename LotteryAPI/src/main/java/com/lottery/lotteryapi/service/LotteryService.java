package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.LotteryDTO;
import com.lottery.lotteryapi.dto.ResultDTO;
import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ResultEntity;
import com.lottery.lotteryapi.repository.DateRepository;
import com.lottery.lotteryapi.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryService {

    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private DateRepository dateRepository;


    public List<LotteryDTO> getLotteriesToday() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        return getLotteries(day + "-" + month + "-" + year);
    }

    public List<LotteryDTO> getLotteries(String date) {
        int day;
        int month;
        int year;
        List<LotteryDTO> results = new ArrayList<>();
        try {
            day = Integer.parseInt(date.split("-")[0]);
            month = Integer.parseInt(date.split("-")[1]);
            year = Integer.parseInt(date.split("-")[2]);
            DateEntity dateEntity = dateRepository.findOneByDateAndMonthAndYear(day, month, year);
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

                results.add(lotteryDTO);lotteryDTO.setResults(lotteryResults);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
