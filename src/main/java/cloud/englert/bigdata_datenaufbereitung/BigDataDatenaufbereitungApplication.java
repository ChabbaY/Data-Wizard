package cloud.englert.bigdata_datenaufbereitung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the spring boot application. Searches annotated classes and creates the services.
 *
 * @author Linus Englert
 */
@SpringBootApplication
public class BigDataDatenaufbereitungApplication {
    /**
     * Starts the application.
     *
     * @param args optional arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BigDataDatenaufbereitungApplication.class, args);
    }
}