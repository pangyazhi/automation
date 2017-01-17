package automation.centre;

import automation.centre.models.Project;
import automation.centre.models.TestSuite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AutomationApplication.class})
public class AutomationApplicationTests {


    static Logger logger = LoggerFactory.getLogger("ApplicationTest");


    @Before
    public void setUp() {
        //RepositoryFactory.getInstance().setRepository(repository);
        RepositoryFactory.getInstance().deleteAll();
    }

    @Test
    public void contextLoads() {
        Project project = new Project();
        project.setName("Project");
        project.setVersion("1.0");
        project.setBuild("1.0.3");
        project = (Project) RepositoryFactory.getInstance().create(project);
        RepositoryFactory.getInstance().clone(project);

        TestSuite testSuite = new TestSuite();
        testSuite.setName("TestSuite");
        project.addSuite(testSuite);
        RepositoryFactory.getInstance().save(testSuite);
        testSuite = (TestSuite) RepositoryFactory.getInstance().clone(testSuite);

        project.addSuite(testSuite);
        RepositoryFactory.getInstance().update(project);
        logger.info("project json:"+project.toJson());
    }

}
