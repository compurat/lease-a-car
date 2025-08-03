package com.lease.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRateDao, Long> {
    InterestRateDao findByStartDate(LocalDate interestRateStartDate);
}