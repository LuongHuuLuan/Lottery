package com.lottery.lotteryapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "source_dim")
public class SourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sour")
    private int idSour;
    @Column
    private String name;
    @Column
    private String url;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LotteryEntity> lotteries;

    public int getIdSour() {
        return idSour;
    }

    public void setIdSour(int idSour) {
        this.idSour = idSour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<LotteryEntity> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<LotteryEntity> lotteries) {
        this.lotteries = lotteries;
    }
}
