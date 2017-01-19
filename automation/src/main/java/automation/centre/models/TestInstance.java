package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class TestInstance extends Model {
    @DBRef
    private LinkedList<TestSuite> testSuites = new LinkedList<>();
    @DBRef
    private TestEnvironment environment;
    @DBRef
    private LinkedList<TestResult> results = new LinkedList<>();
    @DBRef
    private Task lastTask;

    public TestInstance() {
        super();
        this.setType("TestInstance");
    }

    public LinkedList<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(LinkedList<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public TestEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(TestEnvironment environment) {
        this.environment = environment;
    }

    public LinkedList<TestResult> getResults() {
        return results;
    }

    public void setResults(LinkedList<TestResult> results) {
        this.results = results;
    }

    public Task getLastTask() {
        return lastTask;
    }

    public void setLastTask(Task lastTask) {
        this.lastTask = lastTask;
    }
}
