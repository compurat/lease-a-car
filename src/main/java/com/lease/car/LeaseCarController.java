package com.lease.car;

import com.lease.car.file.reading.CsvFileImportService;
import com.lease.car.model.InterestRateData;
import com.lease.car.model.LeaseCar;
import com.lease.car.repository.LeaseCarDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class LeaseCarController {
    private final LeaseCarService leaseCarService;
    private final CsvFileImportService csvFileImportService;
    private final InterestRateService interestRateService;
    private final Logger logger = LoggerFactory.getLogger(LeaseCarController.class);
    LeaseCarController(LeaseCarService leaseCarService, CsvFileImportService csvFileImportService, InterestRateService interestRateService) {
        this.leaseCarService = leaseCarService;
        this.csvFileImportService = csvFileImportService;
        this.interestRateService = interestRateService;
    }

    @PostMapping("/add/car")
    void addCar(@RequestBody LeaseCar leaseCar) {
        leaseCarService.saveCar(leaseCar);
    }

    @PostMapping("/add/multiple/cars")
    void addCars(@RequestBody List<LeaseCar> leaseCars) {
        leaseCarService.saveAllCars(leaseCars);

    }

    @PutMapping("/update")
    void editCar(@RequestBody LeaseCar leaseCar) {
        leaseCarService.updateCar(leaseCar);
    }

    @DeleteMapping("/delete")
    void deleteCar(@RequestBody LeaseCar leaseCar) {
        leaseCarService.deleteCar(leaseCar);
    }

    @GetMapping("/find/{make}")
    List<LeaseCar> getCarsByMake(@PathVariable String make) {
        return leaseCarService.getCarsByMake(make);
    }

    @GetMapping("/find/all")
    List<LeaseCar> getAllCars() {
       return leaseCarService.getAllCars();
    }

    @PostMapping("/load/all")
    void readCsvFileWithCars() {
       csvFileImportService.processCarCsvFileType();
    }

    @PostMapping("/load/interest/rates")
    void readCsvFileWithInterest() {
        csvFileImportService.processInterestCsvFile();
    }

    @PostMapping("/interest/rate")
    Float getInterestRate(@RequestBody InterestRateData interestRateData) {
       return interestRateService.calculateInterest(interestRateData);
    }


}
