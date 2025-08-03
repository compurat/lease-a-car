package com.lease.car;

import com.lease.car.model.LeaseCar;
import com.lease.car.repository.LeaseCarDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeaseCarMapperTest {

    private LeaseCarMapper leaseCarMapper;
    private LeaseCar leaseCar;
    private LeaseCarDao leaseCarDao;

    @BeforeEach
    void setUp() {
        leaseCarMapper = new LeaseCarMapper();
        leaseCar = createTestCar();
        leaseCarDao = createLeaseCarDao();

    }

    @Test
    void testMapFromModelToDao() {
        LeaseCarDao mappedDao = leaseCarMapper.mapFromModelToDao(leaseCar);
        assertTrue(assertModelAndDaoEquals(leaseCar, mappedDao));
    }

    @Test
    void testMapFromDaoToModel() {
        LeaseCar mappedModel = leaseCarMapper.mapFromDaoToModel(leaseCarDao);

        assertTrue(assertDaoAndModelEquals(leaseCarDao, mappedModel));
    }

    @Test
    void testMapFromModelsToDaos() {
        List<LeaseCar> leaseCars = new ArrayList<>();
        leaseCars.add(createTestCar());

        LeaseCar secondCar = new LeaseCar();
        secondCar.setMake("Ford");
        secondCar.setModel("Focus");
        secondCar.setVersion("ST");
        secondCar.setDoors(5);
        secondCar.setGrossPrice(new Float("22000.00"));
        secondCar.setNettPrice(new Float("18000.00"));
        secondCar.setHorsepower(170);
        leaseCars.add(secondCar);

        List<LeaseCarDao> mappedDaos = leaseCarMapper.mapFromModelsToDaos(leaseCars);

        assertEquals(2, mappedDaos.size(), "The mapped list should have the same size as the input list");
        assertTrue(assertModelAndDaoEquals(leaseCars.get(0), mappedDaos.get(0)), "First mapped DAO should match first model");
        assertTrue(assertModelAndDaoEquals(leaseCars.get(1), mappedDaos.get(1)), "Second mapped DAO should match second model");
    }

    @Test
    void testMapFromDaosToModels() {
        List<LeaseCarDao> leaseCarDaos = new ArrayList<>();
        leaseCarDaos.add(createLeaseCarDao());

        LeaseCarDao secondDao = new LeaseCarDao();
        secondDao.setId(2L);
        secondDao.setMake("BMW");
        secondDao.setModel("3 Series");
        secondDao.setVersion("M Sport");
        secondDao.setDoors(4);
        secondDao.setGrossPrice(new Float("35000.00"));
        secondDao.setNettPrice(new Float("29000.00"));
        secondDao.setHorsepower(220);
        leaseCarDaos.add(secondDao);

        List<LeaseCar> mappedModels = leaseCarMapper.mapFromDaosToModels(leaseCarDaos);

        assertEquals(2, mappedModels.size(), "The mapped list should have the same size as the input list");
        assertTrue(assertDaoAndModelEquals(leaseCarDaos.get(0), mappedModels.get(0)), "First mapped model should match first DAO");
        assertTrue(assertDaoAndModelEquals(leaseCarDaos.get(1), mappedModels.get(1)), "Second mapped model should match second DAO");
    }

    @Test
    void testMapFromModelsToDaosWithEmptyList() {
        List<LeaseCar> emptyList = new ArrayList<>();

        List<LeaseCarDao> mappedDaos = leaseCarMapper.mapFromModelsToDaos(emptyList);

        assertEquals(0, mappedDaos.size(), "The mapped list should be empty when the input list is empty");
    }

    @Test
    void testMapFromDaosToModelsWithEmptyList() {
        List<LeaseCarDao> emptyList = new ArrayList<>();

        List<LeaseCar> mappedModels = leaseCarMapper.mapFromDaosToModels(emptyList);

        assertEquals(0, mappedModels.size(), "The mapped list should be empty when the input list is empty");
    }

    @Test
    void testMapFromModelsToDaosWithNullInput() {
        assertThrows(NullPointerException.class, () -> {
            leaseCarMapper.mapFromModelsToDaos(null);
        }, "Should throw NullPointerException when input is null");
    }

    @Test
    void testMapFromDaosToModelsWithNullInput() {
        assertThrows(NullPointerException.class, () -> {
            leaseCarMapper.mapFromDaosToModels(null);
        }, "Should throw NullPointerException when input is null");
    }


    private LeaseCar createTestCar() {
        leaseCar = new LeaseCar();
        leaseCar.setMake("Toyota");
        leaseCar.setModel("Corolla");
        leaseCar.setVersion("Hybrid");
        leaseCar.setDoors(5);
        leaseCar.setGrossPrice(new Float("25000.00"));
        leaseCar.setNettPrice(new Float("20000.00"));
        leaseCar.setHorsepower(150);
        return leaseCar;
    }

    private LeaseCarDao createLeaseCarDao() {
        leaseCarDao = new LeaseCarDao();
        leaseCarDao.setId(1L);
        leaseCarDao.setMake("Honda");
        leaseCarDao.setModel("Civic");
        leaseCarDao.setVersion("Sport");
        leaseCarDao.setDoors(4);
        leaseCarDao.setGrossPrice(new Float("27000.00"));
        leaseCarDao.setNettPrice(new Float("22000.00"));
        leaseCarDao.setHorsepower(180);
        return leaseCarDao;
    }

    private boolean assertDaoAndModelEquals(LeaseCarDao dao, LeaseCar model) {
        return dao.getDoors().equals(model.getDoors()) &&
                Objects.equals(dao.getGrossPrice(), model.getGrossPrice()) &&
                Objects.equals(dao.getNettPrice(), model.getNettPrice()) &&
                dao.getMake().equals(model.getMake()) &&
                dao.getModel().equals(model.getModel()) &&
                dao.getHorsepower().equals(model.getHorsepower()) &&
                dao.getVersion().equals(model.getVersion());
    }

    private boolean assertModelAndDaoEquals(LeaseCar model, LeaseCarDao dao) {
        return model.getDoors().equals(dao.getDoors()) &&
                Objects.equals(model.getGrossPrice(), dao.getGrossPrice()) &&
                Objects.equals(model.getNettPrice(), dao.getNettPrice()) &&
                model.getMake().equals(dao.getMake()) &&
                model.getModel().equals(dao.getModel()) &&
                model.getHorsepower().equals(dao.getHorsepower()) &&
                model.getVersion().equals(dao.getVersion());
    }

}