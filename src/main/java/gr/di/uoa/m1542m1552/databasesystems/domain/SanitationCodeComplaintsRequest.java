package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sanitation_code_complaints_request", schema="public")
public class SanitationCodeComplaintsRequest extends Request {

    @Column(nullable = true)
    private String natureOfCodeViolation;
}