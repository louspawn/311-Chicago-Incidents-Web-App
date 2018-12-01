package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.TreeDebrisRequest;
import gr.di.uoa.m1542m1552.databasesystems.repository.TreeDebrisRequestRepository;

@Service
public class TreeDebrisRequestService {

    @Autowired
    TreeDebrisRequestRepository treeDebrisRequestRepository;

    public TreeDebrisRequest createRequest(TreeDebrisRequest request) {
        return treeDebrisRequestRepository.save(request);
    }

    public Iterable<TreeDebrisRequest> getRequests(){
        return treeDebrisRequestRepository.findAll();
    }

    public TreeDebrisRequest getRequest(Integer requestId){
      return treeDebrisRequestRepository.findOne(requestId);
    }
}