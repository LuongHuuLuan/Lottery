package com.lottery.lotteryapi.repository;

import com.lottery.lotteryapi.entity.DateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDateRepository extends JpaRepository<DateEntity, Integer> {
    public DateEntity findOneByDateAndMonthAndYear(int date, int month, int year);
    public DateEntity findOneByShortDate(String shortDate);
    public DateEntity findOneByIdDate(int id);
}
