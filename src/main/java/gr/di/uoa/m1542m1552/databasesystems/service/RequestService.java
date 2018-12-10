package gr.di.uoa.m1542m1552.databasesystems.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.enumerations.TypeOfServiceRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.RequestRepository;

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

    public Iterable<Request> getRequestsByStreetAddress(String streetAddress){
        return requestRepository.searchByStreetAddress(streetAddress);
    }

    public Page<Request> getRequestsByZipCodeAndStreetAddress(Pageable pageable, Integer zipCode, String streetAddress){
        return requestRepository.findByZipCodeAndStreetAddressContaining(pageable, zipCode, streetAddress);
    }

    public Page getRequestsByStoredFunction1(Pageable pageable, Date fromDate, Date toDate){
        return requestRepository.searchByStoredFunction1(fromDate, toDate, pageable);
    }

    public Page getRequestsByStoredFunction2(Pageable pageable, Date fromDate, Date toDate, String type){
        return requestRepository.searchByStoredFunction2(fromDate, toDate, type, pageable);
    }

    public Page getRequestsByStoredFunction3(Pageable pageable, Date date){
        return requestRepository.searchByStoredFunction3(date, pageable);
    }
}
