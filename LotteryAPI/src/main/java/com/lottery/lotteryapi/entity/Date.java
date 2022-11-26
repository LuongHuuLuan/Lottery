package com.lottery.lotteryapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "date_dim")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idDate;
    @Column
    private String fullDate;
    @Column
    private String day;
    @Column
    private int date;
    @Column
    private int month;
    @Column
    private int year;

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
