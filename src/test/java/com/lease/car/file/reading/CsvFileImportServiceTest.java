package com.lease.car.file.reading;

import com.lease.car.repository.InterestRateRepository;
import com.lease.car.repository.LeaseCarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CsvFileImportServiceTest {

    @Mock
    private LeaseCarRepository leaseCarRepository;
    @Mock
    private InterestRateRepository interestRateRepository;
    @InjectMocks
    private CsvFileImportService csvFileImportService;

    @Test
    void testProcessCarCsvFileType() {
        csvFileImportService.processCarCsvFileType();
        verify(leaseCarRepository, times(1)).saveAll(any());
    }

    @Test
    void testProcessInterestCsvFile() {
         csvFileImportService.processInterestCsvFile();
        verify(interestRateRepository, times(1)).saveAll(any());
    }

}