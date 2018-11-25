package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="garbage_carts_request", schema="public")
public class GarbageCartsRequest extends Request {
    // PK and FK columns are most often indexed, so sharing the PK can reduce
    // the index footprint by half

    // PrimaryKeyJoinColumn vs JoinColumn
    // https://stackoverflow.com/questions/3417097/jpa-difference-between-joincolumn-and-primarykeyjoincolumn

    // One to many - the best way
    // https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/ 

    // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "serviceRequestNumber")
    // private Request request;

    // Entity Inheritance
    // https://vladmihalcea.com/the-best-way-to-use-entity-inheritance-with-jpa-and-hibernate/

    @Column(nullable = true)
    private int numberOfBlackCartsDelivered;
}