package com.lottery.lotteryapi.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idLot;
    @Column
    private String nkIdLot;
    @Column
    private int idDate;
    @Column
    private int idSour;
    @Column
    private int idPro;
    @Column
    private String isDelete;
    @Column
    private Date updateDate;
    @Column
    private Date expriedDate;

    public int getIdLot() {
        return idLot;
    }

    public void setIdLot(int idLot) {
        this.idLot = idLot;
    }

    public String getNkIdLot() {
        return nkIdLot;
    }

    public void setNkIdLot(String nkIdLot) {
        this.nkIdLot = nkIdLot;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public int getIdSour() {
        return idSour;
    }

    public void setIdSour(int idSour) {
        this.idSour = idSour;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getExpriedDate() {
        return expriedDate;
    }

    public void setExpriedDate(Date expriedDate) {
        this.expriedDate = expriedDate;
    }
}
