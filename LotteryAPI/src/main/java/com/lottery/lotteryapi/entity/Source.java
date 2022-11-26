package com.lottery.lotteryapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "source_dim")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idSour;
    @Column
    private String name;
    @Column
    private String url;

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
}
