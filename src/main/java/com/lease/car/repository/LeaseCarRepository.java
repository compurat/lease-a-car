package com.lease.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseCarRepository extends JpaRepository<LeaseCarDao, Long> {

    List<LeaseCarDao> findByMake(String make);

    List<LeaseCarDao> findByModel(String model);

    List<LeaseCarDao> findByMakeAndModel(String make, String model);

    LeaseCarDao findByMakeAndModelAndVersion(String make, String model, String version);

}