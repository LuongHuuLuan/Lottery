package com.lottery.lotteryapi.dto;


public class ResultDTO {
    private String prizeName;
    private String results;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String name) {
        this.prizeName = name;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
