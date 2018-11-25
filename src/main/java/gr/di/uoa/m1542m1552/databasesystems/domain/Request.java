package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="request", schema="public")
@Inheritance(strategy = InheritanceType.JOINED)
public class Request {
    // for custom generator 
    // https://www.baeldung.com/hibernate-identifiers
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "", unique = true, updatable = false, nullable = false)
    int serviceRequestNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private Date creationDate;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    // https://vladmihalcea.com/the-best-way-to-map-an-enum-type-with-jpa-and-hibernate/
    // https://thoughts-on-java.org/jpa-21-type-converter-better-way-to/
 	@Enumerated(EnumType.ORDINAL)
    @Column(updatable = false, nullable = false)
    private TypeOfServiceRequest typeOfServiceRequest;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private int zipCode;

    private double xCoordinate;

    private double yCoordinate;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    // https://vladmihalcea.com/how-to-store-schema-less-eav-entity-attribute-value-data-using-json-and-hibernate/
    // https://stackoverflow.com/questions/40802656/persisting-a-json-object-using-hibernate-and-jpa
    private String location;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "rollNo", nullable = false)
    // private Student student;

    public int getServiceRequestNumber() {
        return this.serviceRequestNumber;
    }

    public void setServiceRequestNumber(int serviceRequestNumber) {
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

    public int getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public double getXCoordinate() {
        return this.xCoordinate;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getYCoordinate() {
        return this.yCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
