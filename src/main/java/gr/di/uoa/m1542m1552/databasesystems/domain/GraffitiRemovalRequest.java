package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="graffiti_removal_request", schema="public")
public class GraffitiRemovalRequest extends Request {

    @Column(nullable = true)
    private String typeOfSurfaceIsOn;

    @Column(nullable = true)
    private String whereIsLocated;
}