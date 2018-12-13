package gr.di.uoa.m1542m1552.databasesystems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gr.di.uoa.m1542m1552.databasesystems.domain.UserHistory;
import gr.di.uoa.m1542m1552.databasesystems.domain.UserHistoryPK;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistory, UserHistoryPK> {

}
