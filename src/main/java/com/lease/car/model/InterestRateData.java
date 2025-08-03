package com.lease.car.model;

import java.time.LocalDate;

public class InterestRateData {
    Integer mileage; 
    Integer duration; 
    String make; 
    String model; 
    String version; 
    LocalDate interestRateStartDate;

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public LocalDate getInterestRateStartDate() {
        return interestRateStartDate;
    }

    public void setInterestRateStartDate(LocalDate interestRateStartDate) {
        this.interestRateStartDate = interestRateStartDate;
    }
}
