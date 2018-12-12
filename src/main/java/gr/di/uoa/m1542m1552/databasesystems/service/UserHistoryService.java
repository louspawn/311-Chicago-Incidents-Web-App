package gr.di.uoa.m1542m1552.databasesystems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.di.uoa.m1542m1552.databasesystems.domain.UserHistory;
import gr.di.uoa.m1542m1552.databasesystems.repository.UserHistoryRepository;

@Service
public class UserHistoryService {

    @Autowired
    UserHistoryRepository userHistoryRepository;

    public UserHistory createUserHistory(UserHistory userHistory) {
        return userHistoryRepository.save(userHistory);
    }

}
