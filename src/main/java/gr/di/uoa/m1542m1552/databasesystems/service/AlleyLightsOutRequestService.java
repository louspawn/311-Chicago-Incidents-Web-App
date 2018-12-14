package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.AlleyLightsOutRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.AlleyLightsOutRequestRepository;

@Service
public class AlleyLightsOutRequestService {

    @Autowired
    AlleyLightsOutRequestRepository alleyLightsOutRequestRepository;

    public AlleyLightsOutRequest createAlleyLightsOutRequest(AlleyLightsOutRequest alleyLightsOutRequest) {
        return alleyLightsOutRequestRepository.save(alleyLightsOutRequest);
    }

}
