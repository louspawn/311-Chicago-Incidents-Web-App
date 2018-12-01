package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.PotHolesRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.PotHolesRequestRepository;

@Service
public class PotHolesRequestService {

    @Autowired
    PotHolesRequestRepository potHolesRequestRepository;

    public PotHolesRequest createRequest(PotHolesRequest request) {
        return potHolesRequestRepository.save(request);
    }

    public Iterable<PotHolesRequest> getRequests(){
        return potHolesRequestRepository.findAll();
    }

    public PotHolesRequest getRequest(Integer requestId){
      return potHolesRequestRepository.findOne(requestId);
    }
}