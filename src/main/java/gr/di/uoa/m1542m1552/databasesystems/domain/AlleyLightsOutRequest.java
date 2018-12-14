package gr.di.uoa.m1542m1552.databasesystems.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="alley_lights_out", schema="public")
public class AlleyLightsOutRequest extends Request {
}
