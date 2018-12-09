package gr.di.uoa.m1542m1552.databasesystems.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
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

    public Iterable<Request> getRequestsByZipCodeOrStreetAddress(Integer zipCode, String streetAddress){
        // return requestRepository.searchByZIPCode(zipCode);
        return requestRepository.findByZipCodeOrStreetAddressContaining(zipCode, streetAddress);
    }

    public Object[] getRequestsByStoredFunction1(Date fromDate, Date toDate){
        return requestRepository.searchByStoredFunction1(fromDate, toDate);
    }

	public Object[] getRequestsByStoredFunctionTest() {
        return requestRepository.searchByStoredFunctionTest();
	}
}
