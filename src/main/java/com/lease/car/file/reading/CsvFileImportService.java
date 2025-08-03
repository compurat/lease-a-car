package com.lease.car.file.reading;

import com.lease.car.repository.InterestRateDao;
import com.lease.car.repository.InterestRateRepository;
import com.lease.car.repository.LeaseCarDao;
import com.lease.car.repository.LeaseCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class CsvFileImportService {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileImportService.class);
    private static final String DELIMITER = ";";
    private final LeaseCarRepository leaseCarRepository;
    private final InterestRateRepository interestRateRepository;
    public CsvFileImportService(LeaseCarRepository leaseCarRepository, InterestRateRepository interestRateRepository) {
        this.leaseCarRepository = leaseCarRepository;
        this.interestRateRepository = interestRateRepository;
    }

    public void processCarCsvFileType() {
        List<List<String>> csvValues = createCsvValues("testdata.csv");
        List<LeaseCarDao> leaseCarDaos = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        for (List<String> csvLine : csvValues) {
            if (!(csvLine.get(0).equals("make"))) {
                LeaseCarDao leaseCarDao = new LeaseCarDao();
                leaseCarDao.setMake(csvLine.get(0));
                leaseCarDao.setModel(csvLine.get(1));
                leaseCarDao.setVersion(csvLine.get(2));
                leaseCarDao.setDoors(Integer.parseInt(csvLine.get(3)));
                try {
                    leaseCarDao.setGrossPrice(numberFormat.parse(csvLine.get(4)).floatValue());
                    leaseCarDao.setNettPrice(numberFormat.parse(csvLine.get(5)).floatValue());
                    leaseCarDao.setHorsepower(numberFormat.parse(csvLine.get(6)).intValue());
                } catch (ParseException pe) {
                    logger.error("Error while parsing number: {}", pe.getMessage());
                    throw new RuntimeException("Error while parsing number: " + pe.getMessage());
                }
                leaseCarDaos.add(leaseCarDao);
            }
        }
        leaseCarRepository.saveAll(leaseCarDaos);
    }

    public void processInterestCsvFile() {
        List<List<String>> csvValues = createCsvValues("testdata_interest.csv");
        List<InterestRateDao> interestRateDaos = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        
        for (List<String> csvLine : csvValues) {
            if (!(csvLine.get(0).equals("interestrate"))) {
                InterestRateDao interestRateDao = new InterestRateDao();
                try {
                    interestRateDao.setRate(numberFormat.parse(csvLine.get(0)).floatValue());
                    interestRateDao.setStartDate(LocalDate.parse(csvLine.get(1), dateFormatter));
                    interestRateDaos.add(interestRateDao);
                } catch (ParseException pe) {
                    logger.error("Error while parsing number: {}", pe.getMessage());
                    throw new RuntimeException("Error while parsing number: " + pe.getMessage());
                }
            }
        }
        interestRateRepository.saveAll(interestRateDaos);
    }

    private List<List<String>> createCsvValues(String fileName) {
        List<List<String>> csvValues = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                csvValues.add(Arrays.asList(values));
            }
        } catch (IOException ioe) {
            logger.error("Error while reading file: {}", ioe.getMessage());
        }
        return csvValues;
    }
}