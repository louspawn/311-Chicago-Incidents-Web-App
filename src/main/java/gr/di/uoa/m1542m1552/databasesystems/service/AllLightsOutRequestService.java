package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.AllLightsOutRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.AllLightsOutRequestRepository;

@Service
public class AllLightsOutRequestService {

    @Autowired
    AllLightsOutRequestRepository allLightsOutRequestRepository;

    public AllLightsOutRequest createAllLightsOutRequest(AllLightsOutRequest allLightsOutRequest) {
        return allLightsOutRequestRepository.save(allLightsOutRequest);
    }

}
