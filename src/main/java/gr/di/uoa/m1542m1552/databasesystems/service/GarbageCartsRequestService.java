package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.GarbageCartsRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.GarbageCartsRequestRepository;

@Service
public class GarbageCartsRequestService {

    @Autowired
    GarbageCartsRequestRepository garbageCartsRequestRepository;

    public GarbageCartsRequest createRequest(GarbageCartsRequest request) {
        return garbageCartsRequestRepository.save(request);
    }

    public Iterable<GarbageCartsRequest> getRequests(){
        return garbageCartsRequestRepository.findAll();
    }

    public GarbageCartsRequest getRequest(Integer requestId){
      return garbageCartsRequestRepository.findOne(requestId);
    }
}