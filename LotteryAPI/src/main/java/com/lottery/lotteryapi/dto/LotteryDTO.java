package com.lottery.lotteryapi.dto;


import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

public class LotteryDTO {
    private int id;
    private String date;
    private String province;
    private List<ResultDTO> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }
}
