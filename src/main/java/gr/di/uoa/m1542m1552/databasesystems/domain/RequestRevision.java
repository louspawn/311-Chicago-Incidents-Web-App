package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import gr.di.uoa.m1542m1552.databasesystems.enumerations.TypeOfServiceRequest;

@Entity
@IdClass(RequestRevisionPK.class)
@Table(name="request_revisions", schema="public")
@Inheritance(strategy = InheritanceType.JOINED)
public class RequestRevision {
    @Id
    @ManyToOne
    @JoinColumns({@JoinColumn(name="request_id", referencedColumnName="id")})
    private Request requestId;

    @Id
    private Date dateOfUpdate;

    @Column(updatable = false, nullable = false)
    private String serviceRequestNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

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

    private String location;

    public Request getId() {
        return this.requestId;
    }

    public void setId(Request requestId) {
        this.requestId = requestId;
    }

    public Date getDateOfUpdate() {
        return this.dateOfUpdate;
    }

    public void setDateOfUpdate(Date dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
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

}
