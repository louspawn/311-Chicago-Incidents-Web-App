package gr.di.uoa.m1542m1552.databasesystems.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;
import gr.di.uoa.m1542m1552.databasesystems.enumerations.TypeOfServiceRequest;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {

    @Query(value = "SELECT * FROM Request r WHERE r.street_address ILIKE :street_address", nativeQuery = true)
    public Iterable<Request> searchByStreetAddress(@Param("street_address") String street_address);

    public Page<Request> findByZipCodeAndStreetAddressContaining(Pageable pageable, Integer zip_code, String street_address);

    @Query(value = "SELECT res.f1, res.f2 FROM function1(?1, ?2) AS res ORDER BY res.f2 DESC \n-- #pageable\n",
           countQuery = "SELECT count(*) FROM function1(?1, ?2)", nativeQuery = true)
    public Page searchByStoredFunction1(Date fromDate, Date toDate, Pageable pageable);

    @Query(value = "SELECT res.f1, res.f2 FROM function2(?1, ?2, ?3) AS res ORDER BY res.f2 DESC \n-- #pageable\n",
           countQuery = "SELECT count(*) FROM function2(?1, ?2, ?3)", nativeQuery = true)
    public Page searchByStoredFunction2(Date fromDate, Date toDate, String type, Pageable pageable);

    @Query(value = "SELECT res.f1, res.f2 FROM function3(?1) AS res ORDER BY res.f1 \n-- #pageable\n",
           countQuery = "SELECT count(*) FROM function3(?1)", nativeQuery = true)
    public Page searchByStoredFunction3(Date fromDate, Pageable pageable);
}
