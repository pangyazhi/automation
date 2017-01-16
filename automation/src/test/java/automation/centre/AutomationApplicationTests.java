package automation.centre;

import automation.centre.models.Project;
import automation.centre.models.TestSuite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AutomationApplication.class})
public class AutomationApplicationTests {


//    Logger logger;

    @Autowired
    Repository repository;

	@Before
	public void setUp(){
	    //RepositoryFactory.getInstance().setRepository(repository);
		RepositoryFactory.getInstance().deleteAll();
	}

	@Test
	public void contextLoads() {
		Project project = new Project();
		project.setName("Project");
		RepositoryFactory.getInstance().save(project);

		TestSuite testSuite = new TestSuite();
		testSuite.setName("TestSuite");
		RepositoryFactory.getInstance().save(testSuite);
	}

}
