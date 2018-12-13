package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(UserHistoryPK.class)
@Table(name="users_history", schema="public")
public class UserHistory {
    @Id
    @ManyToOne
    @JoinColumns({@JoinColumn(name="user_id", referencedColumnName="id")})
    private User userId;

    @Id
    private Date timeStamp;

    @Column(unique = false, updatable = false, nullable = false)
    private String query;

    public User getUserId() {
        return this.userId;
    }

    public void setUserId(User userId) {
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
