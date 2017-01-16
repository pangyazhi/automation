package automation.centre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@ComponentScan
@Configuration
public class AutomationApplication implements CommandLineRunner {

    static Logger logger = LoggerFactory.getLogger("Application");
    @Autowired
    Repository repository;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AutomationApplication.class, args);
        //ctx.getBean(Repository.class);

    }


    @Override
    public void run(String... strings) throws Exception {
        logger.info("Application is running ...");
        RepositoryFactory.getInstance().setRepository(repository);
        logger.info("Records number:" + RepositoryFactory.getInstance().count());
    }
}
