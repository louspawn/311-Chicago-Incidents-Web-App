package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@IdClass(UserHistoryPK.class)
@Table(name="users_history", schema="public")
public class UserHistory {
    @Id
    // @Column(columnDefinition = "", unique = false, updatable = false, nullable = false)
    private String userId;

    @Id
    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(updatable = false, nullable = false)
    // @CreationTimestamp
    private Date timeStamp;

    @Column(unique = false, updatable = false, nullable = false)
    private String query;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
