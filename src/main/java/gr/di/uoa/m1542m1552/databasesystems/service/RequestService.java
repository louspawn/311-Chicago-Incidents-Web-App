package gr.di.uoa.m1542m1552.databasesystems.service;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Iterable<Request> getRequests(){
        return requestRepository.findAll();
    }

    public Request getRequest(Integer requestId){
      return requestRepository.findOne(requestId);
    }
}
