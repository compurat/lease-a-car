package com.lease.car;

import com.lease.car.model.InterestRateData;
import com.lease.car.repository.InterestRateDao;
import com.lease.car.repository.InterestRateRepository;
import com.lease.car.repository.LeaseCarDao;
import com.lease.car.repository.LeaseCarRepository;
import org.springframework.stereotype.Service;

@Service
public class InterestRateService {

    private final InterestRateRepository interestRateRepository;
    private final LeaseCarRepository leaseCarRepository;
    InterestRateService(InterestRateRepository interestRateRepository, LeaseCarRepository leaseCarRepository) {
        this.interestRateRepository = interestRateRepository;
        this.leaseCarRepository = leaseCarRepository;
    }

    public Float calculateInterest(InterestRateData interestRateData) {
        InterestRateDao interestRateDao = interestRateRepository.findByStartDate(interestRateData.getInterestRateStartDate());
        LeaseCarDao leaseCarDao = leaseCarRepository.findByMakeAndModelAndVersion(interestRateData.getMake(), interestRateData.getModel(), interestRateData.getVersion());

        return ((( interestRateData.getMileage() / 12 ) * interestRateData.getDuration() ) / leaseCarDao.getNettPrice()) + ((( interestRateDao.getRate() / 100 ) * leaseCarDao.getNettPrice()) / 12 );

    }
}
