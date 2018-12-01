package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.TreeTrimsRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.TreeTrimsRequestRepository;

@Service
public class TreeTrimsRequestService {

    @Autowired
    TreeTrimsRequestRepository treeTrimsRequestRepository;

    public TreeTrimsRequest createRequest(TreeTrimsRequest request) {
        return treeTrimsRequestRepository.save(request);
    }

    public Iterable<TreeTrimsRequest> getRequests(){
        return treeTrimsRequestRepository.findAll();
    }

    public TreeTrimsRequest getRequest(Integer requestId){
      return treeTrimsRequestRepository.findOne(requestId);
    }
}