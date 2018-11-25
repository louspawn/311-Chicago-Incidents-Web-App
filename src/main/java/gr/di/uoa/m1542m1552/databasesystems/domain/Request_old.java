package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.*;

@Entity
@Table(name="request_old", schema = "public")
public class Request_old {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;

    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(updatable = false, nullable = false)
    // @CreatedDate
    // private Date creationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
