package com.lottery.lotteryapi.service;

import com.lottery.lotteryapi.dto.PrizeDTO;
import com.lottery.lotteryapi.entity.PrizeEntity;
import com.lottery.lotteryapi.repository.IPrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrizeService {
    @Autowired
    IPrizeRepository prizeRepository;

    public List<PrizeDTO> getAllPrizes() {
        List<PrizeDTO> results = new ArrayList<>();
        List<PrizeEntity> prizeEntities = prizeRepository.findAll();
        for (PrizeEntity prizeEntity : prizeEntities) {
            PrizeDTO prizeDTO = new PrizeDTO();
            prizeDTO.setId(prizeEntity.getIdPri());
            prizeDTO.setCode(prizeEntity.getCodePri());
            prizeDTO.setName(prizeEntity.getName());
            prizeDTO.setPrize(prizeEntity.getPrize());
            results.add(prizeDTO);
        }
        return results;
    }

    public PrizeDTO getPrizeByCode(String code) {
        PrizeDTO prizeDTO = null;
        PrizeEntity prizeEntity = prizeRepository.findOneByCodePri(code);
        if (prizeEntity != null) {
            prizeDTO = new PrizeDTO();
            prizeDTO.setId(prizeEntity.getIdPri());
            prizeDTO.setCode(prizeEntity.getCodePri());
            prizeDTO.setName(prizeEntity.getName());
            prizeDTO.setPrize(prizeEntity.getPrize());
        }
        return prizeDTO;
    }
}
