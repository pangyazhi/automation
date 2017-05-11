package automation.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jien.huang on 09/05/2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {

    private static final Logger logger = LoggerFactory.getLogger("Application");
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
