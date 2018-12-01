package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.RodentBaitingRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.RodentBaitingRequestRepository;

@Service
public class RodentBaitingRequestService {

    @Autowired
    RodentBaitingRequestRepository rodentBaitingRequestRepository;

    public RodentBaitingRequest createRequest(RodentBaitingRequest request) {
        return rodentBaitingRequestRepository.save(request);
    }

    public Iterable<RodentBaitingRequest> getRequests(){
        return rodentBaitingRequestRepository.findAll();
    }

    public RodentBaitingRequest getRequest(Integer requestId){
      return rodentBaitingRequestRepository.findOne(requestId);
    }
}