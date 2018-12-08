package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="rodent_baiting_request", schema="public")
public class RodentBaitingRequest extends Request {

    @Column(nullable = true)
    private int numberOfPremisesBaited;

    @Column(nullable = true)
    private int numberOfPremisesWithGarbage;

    @Column(nullable = true)
    private int numberOfPremisesWithRats;

    @Column(nullable = true)
    private String currentActivity;

    @Column(nullable = true)
    private String mostRecentAction;

    public int getNumberOfPremisesBaited() {
        return this.numberOfPremisesBaited;
    }

    public void setNumberOfPremisesBaited(int numberOfPremisesBaited) {
        this.numberOfPremisesBaited = numberOfPremisesBaited;
    }

    public int getNumberOfPremisesWithGarbage() {
        return this.numberOfPremisesWithGarbage;
    }

    public void setNumberOfPremisesWithGarbage(int numberOfPremisesWithGarbage) {
        this.numberOfPremisesWithGarbage = numberOfPremisesWithGarbage;
    }

    public int getNumberOfPremisesWithRats() {
        return this.numberOfPremisesWithRats;
    }

    public void setNumberOfPremisesWithRats(int numberOfPremisesWithRats) {
        this.numberOfPremisesWithRats = numberOfPremisesWithRats;
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
}