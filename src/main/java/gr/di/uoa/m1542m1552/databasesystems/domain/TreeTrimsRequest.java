package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tree_trims_request", schema="public")
public class TreeTrimsRequest extends Request {

    @Column(nullable = true)
    private String whereIsLocated;

    public String getWhereIsLocated() {
        return this.whereIsLocated;
    }

    public void setWhereIsLocated(String whereIsLocated) {
        this.whereIsLocated = whereIsLocated;
    }

}