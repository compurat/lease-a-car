package com.lease.car;

import com.lease.car.model.LeaseCar;
import com.lease.car.repository.LeaseCarDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseCarMapper {

    LeaseCarDao mapFromModelToDao(LeaseCar leaseCar) {
        LeaseCarDao leaseCarDao = new LeaseCarDao();
        leaseCarDao.setDoors(leaseCar.getDoors());
        leaseCarDao.setModel(leaseCar.getModel());
        leaseCarDao.setMake(leaseCar.getMake());
        leaseCarDao.setHorsepower(leaseCar.getHorsepower());
        leaseCarDao.setGrossPrice(leaseCar.getGrossPrice());
        leaseCarDao.setNettPrice(leaseCar.getNettPrice());
        leaseCarDao.setVersion(leaseCar.getVersion());
        return leaseCarDao;
    }

    List<LeaseCarDao> mapFromModelsToDaos(List<LeaseCar> leaseCars) {
        List<LeaseCarDao> leaseCarDaos = new ArrayList<>();
        for (LeaseCar leaseCar : leaseCars) {
            leaseCarDaos.add(mapFromModelToDao(leaseCar));
        }
        return leaseCarDaos;
    }

    LeaseCar mapFromDaoToModel(LeaseCarDao leaseCarDao) {
        LeaseCar leaseCar = new LeaseCar();
        leaseCar.setDoors(leaseCarDao.getDoors());
        leaseCar.setModel(leaseCarDao.getModel());
        leaseCar.setMake(leaseCarDao.getMake());
        leaseCar.setHorsepower(leaseCarDao.getHorsepower());
        leaseCar.setGrossPrice(leaseCarDao.getGrossPrice());
        leaseCar.setNettPrice(leaseCarDao.getNettPrice());
        leaseCar.setVersion(leaseCarDao.getVersion());
        return leaseCar;
    }


    public List<LeaseCar> mapFromDaosToModels(List<LeaseCarDao> byMake) {
        List<LeaseCar> leaseCars = new ArrayList<>();
        for (LeaseCarDao leaseCarDao : byMake) {
            leaseCars.add(mapFromDaoToModel(leaseCarDao));
        }
        return leaseCars;
    }
}
