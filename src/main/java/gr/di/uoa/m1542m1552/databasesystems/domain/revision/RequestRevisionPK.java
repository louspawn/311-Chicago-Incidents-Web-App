package gr.di.uoa.m1542m1552.databasesystems.domain.revision;

import java.io.Serializable;
import java.util.Date;

public class RequestRevisionPK implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer requestId;
    protected Date dateOfUpdate;

    public RequestRevisionPK() {}

    public RequestRevisionPK(Integer requestId, Date dateOfUpdate) {
        this.requestId = requestId;
        this.dateOfUpdate = dateOfUpdate;
    }

    @Override
    public int hashCode() {
        return requestId.hashCode() + dateOfUpdate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        RequestRevisionPK other = (RequestRevisionPK) obj;
        if (!requestId.equals(other.requestId)) return false;
        if (!dateOfUpdate.equals(other.dateOfUpdate)) return false;
        return true;
    }
}
