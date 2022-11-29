package com.lottery.lotteryapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lottery")
public class LotteryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lot")
    private int idLot;
    @Column
    private String nkIdLot;
    @Column(columnDefinition = "varchar(5) default 'FALSE'")
    private String isDelete;
    @Column(columnDefinition = "date default current_timestamp")
    private Date updateDate;
    @Column(columnDefinition = "date default '9999/12/31'")
    private Date expriedDate;

    @ManyToOne
    @JoinColumn(name = "id_date", referencedColumnName = "id_date")
    @JsonBackReference
    private DateEntity date;

    @ManyToOne
    @JoinColumn(name = "id_sour", referencedColumnName = "id_sour")
    @JsonBackReference
    private SourceEntity source;
    @ManyToOne
    @JoinColumn(name = "id_pro", referencedColumnName = "id_pro")
    @JsonBackReference
    private ProvinceEntity province;

    @OneToMany(mappedBy = "lottery", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ResultEntity> results;

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

    public DateEntity getDate() {
        return date;
    }

    public void setDate(DateEntity date) {
        this.date = date;
    }

    public SourceEntity getSource() {
        return source;
    }

    public void setSource(SourceEntity source) {
        this.source = source;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
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

    public List<ResultEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultEntity> results) {
        this.results = results;
    }
}
