package com.lease.car;

import com.lease.car.model.LeaseCar;
import com.lease.car.repository.LeaseCarDao;
import com.lease.car.repository.LeaseCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeaseCarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaseCarService.class);

    private final LeaseCarRepository leaseCarRepository;

    private final LeaseCarMapper leaseCarMapper;

    public LeaseCarService(LeaseCarRepository leaseCarRepository, LeaseCarMapper leaseCarMapper) {
        this.leaseCarRepository = leaseCarRepository;
        this.leaseCarMapper = leaseCarMapper;
    }

    public void saveCar(LeaseCar car) {
        leaseCarRepository.save(leaseCarMapper.mapFromModelToDao(car));
    }

    @Transactional
    public void saveAllCars(List<LeaseCar> cars) {
        leaseCarRepository.saveAll(leaseCarMapper.mapFromModelsToDaos(cars));
    }

    public void updateCar(LeaseCar leaseCar) {
        LeaseCarDao leaseCarDao = leaseCarRepository.findByMakeAndModelAndVersion(leaseCar.getMake(), leaseCar.getModel(), leaseCar.getVersion());
        LeaseCarDao mappedLeasecarDao = leaseCarMapper.mapFromModelToDao(leaseCar);
        mappedLeasecarDao.setId(leaseCarDao.getId());
        leaseCarRepository.save(mappedLeasecarDao);
    }

    public void deleteCar(LeaseCar leaseCar) {
        LeaseCarDao leaseCarDao = leaseCarRepository.findByMakeAndModelAndVersion(leaseCar.getMake(), leaseCar.getModel(), leaseCar.getVersion());
        leaseCarRepository.deleteById(leaseCarDao.getId());
    }

    public List<LeaseCar> getAllCars() {
        return leaseCarMapper.mapFromDaosToModels(leaseCarRepository.findAll());
    }


    public List<LeaseCar> getCarsByMake(String make) {
        return leaseCarMapper.mapFromDaosToModels(leaseCarRepository.findByMake(make));
    }

}