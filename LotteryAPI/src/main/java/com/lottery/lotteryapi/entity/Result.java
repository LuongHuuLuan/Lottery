package com.lottery.lotteryapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "result")
public class Result {
    @Id
    @Column
    private int idLot;
    @Id
    @Column
    private int idPri;
    @Id
    @Column
    private String result;
    @Column
    private String isDelete;
    @Column
    private Date updateDate;
    @Column
    private Date expriedDate;
}
