package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.SanitationCodeComplaintsRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.SanitationCodeComplaintsRequestRepository;

@Service
public class SanitationCodeComplaintsRequestService {

    @Autowired
    SanitationCodeComplaintsRequestRepository sanitationCodeComplaintsRequestRepository;

    public SanitationCodeComplaintsRequest createRequest(SanitationCodeComplaintsRequest request) {
        return sanitationCodeComplaintsRequestRepository.save(request);
    }

    public Iterable<SanitationCodeComplaintsRequest> getRequests(){
        return sanitationCodeComplaintsRequestRepository.findAll();
    }

    public SanitationCodeComplaintsRequest getRequest(Integer requestId){
      return sanitationCodeComplaintsRequestRepository.findOne(requestId);
    }
}