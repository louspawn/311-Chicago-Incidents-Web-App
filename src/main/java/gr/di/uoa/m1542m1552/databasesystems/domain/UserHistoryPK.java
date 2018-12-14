package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.io.Serializable;
import java.util.Date;

public class UserHistoryPK implements Serializable {
    protected Integer userId;
    protected Date timestamp;

    public UserHistoryPK() {}

    public UserHistoryPK(Integer userId, Date timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + timestamp.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UserHistoryPK other = (UserHistoryPK) obj;
        if (!(userId.equals(other.userId))) return false;
        if (!(timestamp.equals(other.timestamp))) return false;
        return true;
    }
}
