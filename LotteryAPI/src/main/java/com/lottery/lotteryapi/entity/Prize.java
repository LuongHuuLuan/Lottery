package com.lottery.lotteryapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prize_dim")
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPri;
    @Column
    private String name;
    @Column
    private double prize;

    public int getIdPri() {
        return idPri;
    }

    public void setIdPri(int idPri) {
        this.idPri = idPri;
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
}
