package com.lottery.lotteryapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "result")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_re")
    private int idRe;
    @Column
    private String result;
    @Column(columnDefinition = "varchar(5) default 'FALSE'")
    private String isDelete;
    @Column(columnDefinition = "date default current_timestamp")
    private Date updateDate;
    @Column(columnDefinition = "date default '9999/12/31'")
    private Date expriedDate;

    @ManyToOne
    @JoinColumn(name = "id_lot", referencedColumnName = "id_lot")
    @JsonBackReference
    private LotteryEntity lottery;
    @ManyToOne
    @JoinColumn(name = "id_pri", referencedColumnName = "id_pri")
    @JsonBackReference
    private PrizeEntity prize;

    public int getIdRe() {
        return idRe;
    }

    public void setIdRe(int idRe) {
        this.idRe = idRe;
    }

    public LotteryEntity getLottery() {
        return lottery;
    }

    public void setLottery(LotteryEntity lottery) {
        this.lottery = lottery;
    }

    public PrizeEntity getPrize() {
        return prize;
    }

    public void setPrize(PrizeEntity prize) {
        this.prize = prize;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
