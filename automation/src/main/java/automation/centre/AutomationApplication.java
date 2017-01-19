package automation.centre;

import automation.centre.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@ComponentScan
@Configuration
@SpringBootApplication
class AutomationApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger("Application");
    @Autowired
    private Repository repository;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AutomationApplication.class, args);
        //ctx.getBean(Repository.class);

    }


    @Override
    public void run(String... strings) throws Exception {
        logger.info("Application is running ...");
        RepositoryFactory.getInstance().setRepository(repository);
        long numberOfRecords = RepositoryFactory.getInstance().count();
        logger.info("Records number:" + numberOfRecords);
        if(numberOfRecords==0)
            initData();
    }

    private void initData() {
        RepositoryFactory.getInstance().deleteAll();
        Panel panel = new Panel();
        panel.setName("Panel");
        RepositoryFactory.getInstance().create(panel);

        UIObject uiObject = new UIObject();
        uiObject.setName("UI Object");
        uiObject.setType("text");
        uiObject.setUiid("name");
        uiObject.setXpath("//text[@id='name']");
        RepositoryFactory.getInstance().create(uiObject);

        panel.addUIObject(uiObject);
        RepositoryFactory.getInstance().update(panel);

        Task task = new Task();
        task.setName("Task");
        RepositoryFactory.getInstance().create(task);

        Variable inputVariable = new Variable();
        inputVariable.setName("Input Variable");
        inputVariable.setValue("Input Value");
        RepositoryFactory.getInstance().create(inputVariable);

        Variable outputVariable = new Variable();
        outputVariable.setName("Output Variable");
        outputVariable.setValue("Output Value");
        RepositoryFactory.getInstance().create(outputVariable);

        TestCase testCase = new TestCase();
        testCase.setName("Test Case");
        testCase.getTasks().add(task);
        testCase.getInputVariables().add(inputVariable);
        testCase.getOutputVariables().add(outputVariable);
        RepositoryFactory.getInstance().create(testCase);

        TestSuite testSuite = new TestSuite();
        testSuite.setName("Test Suite");
        testSuite.getInputVariables().add(inputVariable);
        testSuite.getOutputVariables().add(outputVariable);
        testSuite.getSubTestScripts().add(testCase);
        RepositoryFactory.getInstance().save(testSuite);

        Project project = new Project();
        project.setName("Project");
        project.setVersion("7.0");
        project.setBuild("7.0.0.1");
        project.getPanels().add(panel);
        project.getSuites().add(testSuite);
        RepositoryFactory.getInstance().create(project);

        TestClient client = new TestClient();
        client.setName("Client");
        RepositoryFactory.getInstance().create(client);
        TestClient host = new TestClient();
        host.setName("Host");
        RepositoryFactory.getInstance().create(host);

        TestEnvironment testEnvironment = new TestEnvironment();
        testEnvironment.setName("Test Environment");
        testEnvironment.setClient(client);
        testEnvironment.setHost(host);
        testEnvironment.getSettings().add(inputVariable);
        testEnvironment.getSettings().add(outputVariable);
        RepositoryFactory.getInstance().create(testEnvironment);

        TestResult testResult = new TestResult();
        testResult.setName("Test Result");
        RepositoryFactory.getInstance().create(testResult);

        TestInstance testInstance = new TestInstance();
        testInstance.setName("Test Instance");
        testInstance.setEnvironment(testEnvironment);
        testInstance.getTestSuites().add(testSuite);
        testInstance.getResults().add(testResult);
        testInstance.setLastTask(task);
        RepositoryFactory.getInstance().create(testInstance);
    }
}
