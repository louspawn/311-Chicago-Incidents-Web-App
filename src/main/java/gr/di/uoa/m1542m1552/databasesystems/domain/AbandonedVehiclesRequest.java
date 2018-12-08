package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="abandoned_vehicles_request", schema="public")
public class AbandonedVehiclesRequest extends Request {

    @Column(columnDefinition = "varchar(500)", nullable = true)
    private String licensePlate;

    @Column(nullable = true)
    private String vehicleModel;

    @Column(nullable = true)
    private String vehicleColor;

    @Column(nullable = true)
    private String currentActivity;

    @Column(nullable = true)
    private String mostRecentAction;

    @Column(nullable = true)
    private int daysReported;

    @Column(nullable = true)
    private int ssa;

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleModel() {
        return this.vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColor() {
        return this.vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getCurrentActivity() {
        return this.currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getMostRecentAction() {
        return this.mostRecentAction;
    }

    public void setMostRecentAction(String mostRecentAction) {
        this.mostRecentAction = mostRecentAction;
    }

    public int getDaysReported() {
        return this.daysReported;
    }

    public void setDaysReported(int daysReported) {
        this.daysReported = daysReported;
    }

    public int getSsa() {
        return this.ssa;
    }

    public void setSsa(int ssa) {
        this.ssa = ssa;
    }
}