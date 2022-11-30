package com.lottery.lotteryapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prize_dim")
public class PrizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pri")
    private int idPri;
    @Column
    private String codePri;
    @Column
    private String name;
    @Column
    private double prize;

    @OneToMany(mappedBy = "prize", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ResultEntity> results;

    public int getIdPri() {
        return idPri;
    }

    public void setIdPri(int idPri) {
        this.idPri = idPri;
    }

    public String getCodePri() {
        return codePri;
    }

    public void setCodePri(String codePri) {
        this.codePri = codePri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public List<ResultEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultEntity> results) {
        this.results = results;
    }
}
