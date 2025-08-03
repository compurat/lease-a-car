package com.lease.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InterrestRateRepository extends JpaRepository<InterestRateDao, Long> {
    InterestRateDao findByStartDate(LocalDate interestRateStartDate);
}
