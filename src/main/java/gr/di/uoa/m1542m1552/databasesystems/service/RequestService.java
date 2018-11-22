package gr.di.uoa.m1542m1552.databasesystems.service;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @ResponseBody
    public Request createRequest(Request request) {
        requestRepository.save(request);
        return request;
    }

    @ResponseBody
    public Iterable<Request> getRequests(){
        return requestRepository.findAll();
    }

    @ResponseBody
    public Request getRequest(Integer requestId){
      return requestRepository.findOne(requestId);
    }
}
