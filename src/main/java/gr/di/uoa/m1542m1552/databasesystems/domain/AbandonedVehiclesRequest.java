package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="abandoned_vehicles_request", schema="public")
public class AbandonedVehiclesRequest extends Request {

    @Column(nullable = true)
    private String licencePlate;

    @Column(nullable = true)
    private String vehicleModel;

    @Column(nullable = true)
    private String vehicleColour;

    @Column(nullable = true)
    private String currentActivity;

    @Column(nullable = true)
    private String mostRecentAction;

    @Column(nullable = true)
    private int daysReported;

    @Column(nullable = true)
    private int ssa;
}