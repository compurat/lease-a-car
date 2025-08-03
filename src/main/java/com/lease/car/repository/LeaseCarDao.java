package com.lease.car.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "lease_cars")
@Table(name = "cars")
public class LeaseCarDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "co2_emission")
    private String co2Emission;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private Integer doors;

    @Column(name = "gross_price", precision = 10, scale = 2)
    private Float grossPrice;

    @Column(name = "nett_price", precision = 10, scale = 2)
    private Float nettPrice;

    @Column(nullable = false)
    private Integer horsepower;

    public LeaseCarDao() {
    }

    public LeaseCarDao(String make, String model, String version, Integer doors,
                       Float grossPrice, Float nettPrice, Integer horsepower) {
        this.make = make;
        this.model = model;
        this.version = version;
        this.doors = doors;
        this.grossPrice = grossPrice;
        this.nettPrice = nettPrice;
        this.horsepower = horsepower;
    }

    public Long getId() {
        return id;
    }

    public String getCo2Emission() {
        return co2Emission;
    }
    public void setCo2Emission(String co2Emission) {
        this.co2Emission = co2Emission;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Float getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Float grossPrice) {
        this.grossPrice = grossPrice;
    }

    public Float getNettPrice() {
        return nettPrice;
    }

    public void setNettPrice(Float nettPrice) {
        this.nettPrice = nettPrice;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

}