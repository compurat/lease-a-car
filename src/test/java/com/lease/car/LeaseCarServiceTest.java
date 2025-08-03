package com.lease.car;

import com.lease.car.model.LeaseCar;
import com.lease.car.repository.LeaseCarDao;
import com.lease.car.repository.LeaseCarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeaseCarServiceTest {

    @Mock
    private LeaseCarRepository leaseCarRepository;

    @Mock
    private LeaseCarMapper leaseCarMapper;

    @InjectMocks
    private LeaseCarService leaseCarService;

    private LeaseCar testCar;
    private LeaseCarDao testCarDao;
    private List<LeaseCar> testCars;
    private List<LeaseCarDao> testCarDaos;

    @BeforeEach
    void setUp() {
        testCar = createTestCar();
        testCarDao = createTestCarDao();
        testCars = createTestCarList();
        testCarDaos = createTestCarDaoList();
    }

    @Test
    void testSaveCar() {
        when(leaseCarMapper.mapFromModelToDao(testCar)).thenReturn(testCarDao);
        when(leaseCarRepository.save(testCarDao)).thenReturn(testCarDao);

       leaseCarService.saveCar(testCar);

       verify(leaseCarRepository, times(1)).save(testCarDao);
    }

    @Test
    void testSaveAllCars() {
        when(leaseCarMapper.mapFromModelsToDaos(testCars)).thenReturn(testCarDaos);
        when(leaseCarRepository.saveAll(testCarDaos)).thenReturn(testCarDaos);

        leaseCarService.saveAllCars(testCars);

        verify(leaseCarMapper, times(1)).mapFromModelsToDaos(testCars);
        verify(leaseCarRepository, times(1)).saveAll(testCarDaos);
    }

    @Test
    void testUpdateCar() {
        when(leaseCarRepository.findByMakeAndModelAndVersion(
                testCar.getMake(), testCar.getModel(), testCar.getVersion()))
                .thenReturn(testCarDao);
        when(leaseCarMapper.mapFromModelToDao(testCar)).thenReturn(testCarDao);
        when(leaseCarRepository.save(testCarDao)).thenReturn(testCarDao);

        leaseCarService.updateCar(testCar);

        verify(leaseCarRepository, times(1))
                .findByMakeAndModelAndVersion(testCar.getMake(), testCar.getModel(), testCar.getVersion());
        verify(leaseCarMapper, times(1)).mapFromModelToDao(testCar);
        verify(leaseCarRepository, times(1)).save(testCarDao);
    }

    @Test
    void testDeleteCar() {
        when(leaseCarRepository.findByMakeAndModelAndVersion(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(testCarDao);
        leaseCarService.deleteCar(testCar);

        verify(leaseCarRepository, times(1)).deleteById(testCarDao.getId());
    }

    @Test
    void testGetAllCars() {
        when(leaseCarRepository.findAll()).thenReturn(testCarDaos);
        when(leaseCarMapper.mapFromDaosToModels(testCarDaos)).thenReturn(testCars);
        List<LeaseCar> result = leaseCarService.getAllCars();

        assertEquals(testCarDaos.size(), result.size());
        verify(leaseCarRepository, times(1)).findAll();
    }

    @Test
    void testGetCarsByMake() {
        String make = "Toyota";
        when(leaseCarRepository.findByMake(make)).thenReturn(testCarDaos);
        when(leaseCarMapper.mapFromDaosToModels(testCarDaos)).thenReturn(testCars);

        List<LeaseCar> result = leaseCarService.getCarsByMake(make);

        assertEquals(testCars, result);
        verify(leaseCarRepository, times(1)).findByMake(make);
        verify(leaseCarMapper, times(1)).mapFromDaosToModels(testCarDaos);
    }

    @Test
    void testGetCarsByMakeWithEmptyResult() {
        String make = "NonExistentMake";
        when(leaseCarRepository.findByMake(make)).thenReturn(Collections.emptyList());
        when(leaseCarMapper.mapFromDaosToModels(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<LeaseCar> result = leaseCarService.getCarsByMake(make);

        assertTrue(result.isEmpty());
        verify(leaseCarRepository, times(1)).findByMake(make);
        verify(leaseCarMapper, times(1)).mapFromDaosToModels(Collections.emptyList());
    }

    @Test
    void testSaveCarWithNullInput() {
        // Setup - Expecting NullPointerException when null is passed to mapFromModelToDao
        when(leaseCarMapper.mapFromModelToDao(null)).thenThrow(NullPointerException.class);

        // Execute & Verify
        assertThrows(NullPointerException.class, () -> {
            leaseCarService.saveCar(null);
        });
    }

    @Test
    void testSaveAllCarsWithEmptyList() {
        // Setup
        List<LeaseCar> emptyList = Collections.emptyList();
        when(leaseCarMapper.mapFromModelsToDaos(emptyList)).thenReturn(Collections.emptyList());

        // Execute
        leaseCarService.saveAllCars(emptyList);

        // Verify
        verify(leaseCarMapper, times(1)).mapFromModelsToDaos(emptyList);
        verify(leaseCarRepository, times(1)).saveAll(Collections.emptyList());
    }

    @Test
    void testUpdateCarWhenCarNotFound() {
        // Setup - Car not found in repository
        when(leaseCarRepository.findByMakeAndModelAndVersion(
                testCar.getMake(), testCar.getModel(), testCar.getVersion()))
                .thenReturn(null);

        // Execute & Verify
        assertThrows(NullPointerException.class, () -> {
            leaseCarService.updateCar(testCar);
        });

        verify(leaseCarRepository, times(1))
                .findByMakeAndModelAndVersion(testCar.getMake(), testCar.getModel(), testCar.getVersion());
        verify(leaseCarMapper, times(1)).mapFromModelToDao(testCar);
    }

    @Test
    void testDeleteCarWithNullInput() {
        assertThrows(NullPointerException.class, () -> {
            leaseCarService.deleteCar(null);
        });
    }

    @Test
    void testGetAllCarsWithEmptyResult() {
        // Setup
        when(leaseCarRepository.findAll()).thenReturn(Collections.emptyList());

        // Execute
        List<LeaseCar> result = leaseCarService.getAllCars();

        // Verify
        assertTrue(result.isEmpty());
        verify(leaseCarRepository, times(1)).findAll();
    }

    // Helper methods to create test data
    private LeaseCar createTestCar() {
        LeaseCar car = new LeaseCar();
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setVersion("Hybrid");
        car.setDoors(5);
        car.setGrossPrice(new Float("25000.00"));
        car.setNettPrice(new Float("20000.00"));
        car.setHorsepower(150);
        return car;
    }

    private LeaseCarDao createTestCarDao() {
        LeaseCarDao carDao = new LeaseCarDao();
        carDao.setId(1L);
        carDao.setMake("Toyota");
        carDao.setModel("Corolla");
        carDao.setVersion("Hybrid");
        carDao.setDoors(5);
        carDao.setGrossPrice(new Float("25000.00"));
        carDao.setNettPrice(new Float("20000.00"));
        carDao.setHorsepower(150);
        return carDao;
    }

    private List<LeaseCar> createTestCarList() {
        List<LeaseCar> cars = new ArrayList<>();
        cars.add(createTestCar());

        // Add a second car
        LeaseCar secondCar = new LeaseCar();
        secondCar.setMake("Honda");
        secondCar.setModel("Civic");
        secondCar.setVersion("Sport");
        secondCar.setDoors(4);
        secondCar.setGrossPrice(new Float("27000.00"));
        secondCar.setNettPrice(new Float("22000.00"));
        secondCar.setHorsepower(180);
        cars.add(secondCar);

        return cars;
    }

    private List<LeaseCarDao> createTestCarDaoList() {
        List<LeaseCarDao> carDaos = new ArrayList<>();
        carDaos.add(createTestCarDao());

        // Add a second car DAO
        LeaseCarDao secondCarDao = new LeaseCarDao();
        secondCarDao.setId(2L);
        secondCarDao.setMake("Honda");
        secondCarDao.setModel("Civic");
        secondCarDao.setVersion("Sport");
        secondCarDao.setDoors(4);
        secondCarDao.setGrossPrice(new Float("27000.00"));
        secondCarDao.setNettPrice(new Float("22000.00"));
        secondCarDao.setHorsepower(180);
        carDaos.add(secondCarDao);

        return carDaos;
    }
}