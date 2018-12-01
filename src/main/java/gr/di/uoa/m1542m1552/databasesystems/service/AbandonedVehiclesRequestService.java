package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.AbandonedVehiclesRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.AbandonedVehiclesRequestRepository;

@Service
public class AbandonedVehiclesRequestService {

    @Autowired
    AbandonedVehiclesRequestRepository abandonedVehiclesRequestRepository;

    public AbandonedVehiclesRequest createRequest(AbandonedVehiclesRequest request) {
        return abandonedVehiclesRequestRepository.save(request);
    }

    public Iterable<AbandonedVehiclesRequest> getRequests(){
        return abandonedVehiclesRequestRepository.findAll();
    }

    public AbandonedVehiclesRequest getRequest(Integer requestId){
      return abandonedVehiclesRequestRepository.findOne(requestId);
    }
}