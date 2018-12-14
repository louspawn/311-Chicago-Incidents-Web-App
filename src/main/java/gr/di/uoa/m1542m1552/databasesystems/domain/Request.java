package gr.di.uoa.m1542m1552.databasesystems.domain;

import gr.di.uoa.m1542m1552.databasesystems.domain.revision.RequestRevision;
import gr.di.uoa.m1542m1552.databasesystems.enumerations.*;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="request", schema="public",
       indexes = { @Index(name = "creation_date_idx",  columnList="creationDate"),
                   @Index(name = "type_of_service_request_creation_date_idx",  columnList="typeOfServiceRequest, creationDate"),
                   @Index(name = "creation_date_zip_code_idx",  columnList="creationDate, zipCode"),
                   @Index(name = "zip_code_idx",  columnList="zipCode"),
                   @Index(name = "street_address_idx",  columnList="streetAddress"),
                   @Index(name = "creation_date_type_of_service_request_idx",  columnList="creationDate, typeOfServiceRequest"),
                   @Index(name = "type_of_service_request_idx",  columnList="typeOfServiceRequest")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Request {
    // for custom generator 
    // https://www.baeldung.com/hibernate-identifiers
    @Id
    @Column(columnDefinition = "", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(updatable = false, nullable = false)
    private String serviceRequestNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    // TODO: uncomment after db import
    @CreationTimestamp
    private Date creationDate;

    @Column(nullable = false)
    private String status;

    // TODO: only date
    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    // https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
    // https://thoughts-on-java.org/jpa-21-type-converter-better-way-to/
 	@Enumerated(EnumType.STRING)
    @Column(updatable = false, nullable = false)
    private TypeOfServiceRequest typeOfServiceRequest;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = true)
    private Integer streetNumber;

    @Column(nullable = true)
    private Integer zipCode;

    @Column(nullable = true)
    private Double xCoordinate;

    @Column(nullable = true)
    private Double yCoordinate;

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    // https://vladmihalcea.com/how-to-store-schema-less-eav-entity-attribute-value-data-using-json-and-hibernate/
    // https://stackoverflow.com/questions/40802656/persisting-a-json-object-using-hibernate-and-jpa
    private String location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "request_id")
    private Collection<RequestRevision> requestRevisions;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceRequestNumber() {
        return this.serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompletionDate() {
        return this.completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public TypeOfServiceRequest getTypeOfServiceRequest() {
        return this.typeOfServiceRequest;
    }

    public void setTypeOfServiceRequest(TypeOfServiceRequest typeOfServiceRequest) {
        this.typeOfServiceRequest = typeOfServiceRequest;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Integer getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Double getXCoordinate() {
        return this.xCoordinate;
    }

    public void setXCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getYCoordinate() {
        return this.yCoordinate;
    }

    public void setYCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Collection<RequestRevision> getRequestRevision() {
        return requestRevisions;
    }

    public void setUserHistory(Collection<RequestRevision> requestRevisions) {
        this.requestRevisions = requestRevisions;
    }

}
