
package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.io.Serializable;
import java.util.Date;

public class UserHistoryPK implements Serializable {
    protected String userId;
    protected Date timeStamp;

    public UserHistoryPK() {}

    public UserHistoryPK(String userId, Date timeStamp) {
        this.userId = userId;
        this.timeStamp = timeStamp;
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + timeStamp.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UserHistoryPK other = (UserHistoryPK) obj;
        if (userId.equals(other.userId)) return false;
        if (timeStamp.equals(other.timeStamp)) return false;
        return true;
    }
}
