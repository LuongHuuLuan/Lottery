package com.lottery.lotteryapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "province_dim")
public class ProvinceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pro")
    private int idPro;
    @Column
    private String codePro;
    @Column
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LotteryEntity> lotteries;

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getCodePro() {
        return codePro;
    }

    public void setCodePro(String codePro) {
        this.codePro = codePro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LotteryEntity> getLotteries() {
        return lotteries;
    }

    public void setLotteries(List<LotteryEntity> lotteries) {
        this.lotteries = lotteries;
    }
}
