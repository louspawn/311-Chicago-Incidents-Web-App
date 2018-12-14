package gr.di.uoa.m1542m1552.databasesystems.domain;

import java.io.Serializable;
import java.util.Date;

public class RequestRevisionsPK implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer id;
    protected Date dateOfUpdate;

    public RequestRevisionsPK() {
    }

    public RequestRevisionsPK(Integer id, Date dateOfUpdate) {
        this.id = id;
        this.dateOfUpdate = dateOfUpdate;
    }

    @Override
    public int hashCode() {
        return id.hashCode() + dateOfUpdate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        RequestRevisionsPK other = (RequestRevisionsPK) obj;
        if (!(id.equals(other.id))) return false;
        if (!(dateOfUpdate.equals(other.dateOfUpdate))) return false;
        return true;
    }
}
