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

    @Column(nullable = true)
    private Integer ssa;

    public String getTypeOfSurfaceIsOn() {
        return this.typeOfSurfaceIsOn;
    }

    public void setTypeOfSurfaceIsOn(String typeOfSurfaceIsOn) {
        this.typeOfSurfaceIsOn = typeOfSurfaceIsOn;
    }

    public String getWhereIsLocated() {
        return this.whereIsLocated;
    }

    public void setWhereIsLocated(String whereIsLocated) {
        this.whereIsLocated = whereIsLocated;
    }

    public Integer getSsa() {
        return this.ssa;
    }

    public void setSsa(Integer ssa) {
        this.ssa = ssa;
    }
}