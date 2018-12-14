package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.StreetLightOutRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.StreetLightOutRequestRepository;

@Service
public class StreetLightOutRequestService {

    @Autowired
    StreetLightOutRequestRepository streetLightOutRequestRepository;

    public StreetLightOutRequest createStreetLightOutRequest(StreetLightOutRequest streetLightOutRequest) {
        return streetLightOutRequestRepository.save(streetLightOutRequest);
    }

}
