package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.GraffitiRemovalRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.GraffitiRemovalRequestRepository;

@Service
public class GraffitiRemovalRequestService {

    @Autowired
    GraffitiRemovalRequestRepository graffitiRemovalRequestRepository;

    public GraffitiRemovalRequest createRequest(GraffitiRemovalRequest request) {
        return graffitiRemovalRequestRepository.save(request);
    }

    public Iterable<GraffitiRemovalRequest> getRequests(){
        return graffitiRemovalRequestRepository.findAll();
    }

    public GraffitiRemovalRequest getRequest(Integer requestId){
      return graffitiRemovalRequestRepository.findOne(requestId);
    }
}