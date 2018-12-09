package gr.di.uoa.m1542m1552.databasesystems.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.di.uoa.m1542m1552.databasesystems.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    // @Query("select u from users u where u.email = ?1")
    // User findByEmail(String email);

    @Query(value = "select email from users u where u.email = :email", nativeQuery = true)
	public String findByEmail(@Param("email") String email);
}
