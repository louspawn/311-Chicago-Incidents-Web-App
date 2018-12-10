package gr.di.uoa.m1542m1552.databasesystems.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.di.uoa.m1542m1552.databasesystems.domain.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {

    // @Query(value = "SELECT * FROM request r WHERE r.street_address ILIKE '%?1%''", nativeQuery = true)
    // public Iterable<Request> searchByStreetAddress(String street_address);

    @Query(value = "SELECT * FROM Request r WHERE r.street_address ILIKE :street_address", nativeQuery = true)
    public Iterable<Request> searchByStreetAddress(@Param("street_address") String street_address);

    @Query(value = "SELECT id, completion_date, creation_date, latitude, longitude, service_request_number, status, street_address, type_of_service_request, x_coordinate, y_coordinate, zip_code FROM request WHERE zip_code = :zip_code", nativeQuery = true)
	// public Iterable<Request> searchByZIPCode(Integer zip_code);
	public Iterable<Request> searchByZIPCode(@Param("zip_code") Integer zip_code);
    
    @Query(value = "SELECT res.f1, res.f2 FROM function1(?1, ?2) AS res ORDER BY res.f2 DESC \n-- #pageable\n",
           countQuery = "SELECT count(*) FROM function1(?1, ?2)", nativeQuery = true)
    public Page searchByStoredFunction1(Date fromDate, Date toDate, Pageable pageable);

    @Query(value = "SELECT * FROM functionTest()", nativeQuery = true)
    public Object[] searchByStoredFunctionTest();

	public Page<Request> findByZipCodeAndStreetAddressContaining(Pageable pageable, Integer zip_code, String street_address);
}
