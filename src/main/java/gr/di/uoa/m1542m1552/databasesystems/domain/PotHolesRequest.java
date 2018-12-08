package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pot_holes_request", schema="public")
public class PotHolesRequest extends Request {

    @Column(nullable = true)
    private String currentActivity;

    @Column(nullable = true)
    private String mostRecentAction;

    @Column(nullable = true)
    private int numberOfPotHolesFilledOnBlock;

    @Column(nullable = true)
    private int ssa;

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

    public int getNumberOfPotHolesFilledOnBlock() {
        return this.numberOfPotHolesFilledOnBlock;
    }

    public void setNumberOfPotHolesFilledOnBlock(int numberOfPotHolesFilledOnBlock) {
        this.numberOfPotHolesFilledOnBlock = numberOfPotHolesFilledOnBlock;
    }

    public int getSsa() {
        return this.ssa;
    }

    public void setSsa(int ssa) {
        this.ssa = ssa;
    }
}