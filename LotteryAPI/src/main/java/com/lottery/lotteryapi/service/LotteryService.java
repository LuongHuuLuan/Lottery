package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.LotteryDTO;
import com.lottery.lotteryapi.dto.ResultDTO;
import com.lottery.lotteryapi.entity.DateEntity;
import com.lottery.lotteryapi.entity.LotteryEntity;
import com.lottery.lotteryapi.entity.ResultEntity;
import com.lottery.lotteryapi.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryService {

    @Autowired
    private DateRepository dateRepository;


    public List<LotteryDTO> getTodayLottery() {
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        return getLottery(day + "-" + month + "-" + year);
    }

    public List<LotteryDTO> getLottery(String date) {
        int day;
        int month ;
        int year;
        List<LotteryDTO> results = new ArrayList<>();
        try {
            day = Integer.parseInt(date.split("-")[0]);
            month = Integer.parseInt(date.split("-")[1]);
            year = Integer.parseInt(date.split("-")[2]);
            DateEntity dateEntity = dateRepository.findOneByDateAndMonthAndYear(day, month, year);
            if (dateEntity.getLotteries().size() != 0) {
                List<LotteryEntity> lotteries = dateEntity.getLotteries();
                if (lotteries.size() != 0) {
                    for (LotteryEntity lottery : lotteries) {
                        if (lottery.getIsDelete().equals("FALSE")) {
                            LotteryDTO lotteryDTO = new LotteryDTO();
                            lotteryDTO.setId(lottery.getIdLot());
                            lotteryDTO.setDate(lottery.getDate().getFullDate());
                            lotteryDTO.setProvince(lottery.getProvince().getName());
                            List<ResultDTO> lotteryResults = new ArrayList<>();
                            for (ResultEntity resultEntity : lottery.getResults()) {
                                ResultDTO resultDTO = new ResultDTO();
                                resultDTO.setPrizeName(resultEntity.getPrize().getName());
                                resultDTO.setResults(resultEntity.getResult());
                                lotteryResults.add(resultDTO);
                            }
                            lotteryDTO.setResults(lotteryResults);
                            results.add(lotteryDTO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
