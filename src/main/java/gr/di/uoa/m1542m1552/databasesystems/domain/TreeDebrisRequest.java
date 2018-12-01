package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tree_debris_request", schema="public")
public class TreeDebrisRequest extends Request {

    @Column(nullable = true)
    private String currentActivity;

    @Column(nullable = true)
    private String mostRecentAction;

    @Column(nullable = true)
    private String whereIsLocated;
}