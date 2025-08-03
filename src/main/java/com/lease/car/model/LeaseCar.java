package com.lease.car.model;

import java.math.BigDecimal;

public class LeaseCar {

    private String make;
    private String model;
    private String version;
    private Integer doors;
    private Float grossPrice;
    private Float nettPrice;
    private Integer horsepower;

    public LeaseCar() {
    }

    public LeaseCar(String make, String model, String version, Integer doors,
                    Float grossPrice, Float nettPrice, Integer horsepower) {
        this.make = make;
        this.model = model;
        this.version = version;
        this.doors = doors;
        this.grossPrice = grossPrice;
        this.nettPrice = nettPrice;
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