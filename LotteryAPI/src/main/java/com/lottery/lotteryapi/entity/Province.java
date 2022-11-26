package com.lottery.lotteryapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "province_dim")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idPro;
    @Column
    private String name;

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
