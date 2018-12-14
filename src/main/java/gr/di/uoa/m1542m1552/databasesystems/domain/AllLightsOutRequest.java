package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="all_lights_out", schema="public")
public class AllLightsOutRequest extends Request {
}
