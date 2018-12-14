package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.RequestRevision;
import gr.di.uoa.m1542m1552.databasesystems.repository.RequestRevisionRepository;

@Service
public class RequestRevisionService {

    @Autowired
    RequestRevisionRepository requestRevisionRepository;

    public RequestRevision createRequestRevision(RequestRevision requestRevision) {
        return requestRevisionRepository.save(requestRevision);
    }

}
