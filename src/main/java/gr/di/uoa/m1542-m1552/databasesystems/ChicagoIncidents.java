package gr.di.uoa.pliakos.databasesystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChicagoIncidents {
    public static void main(String [] argv){
        SpringApplication.run(SpringBootPostgresExample.class, argv);
    }
}
