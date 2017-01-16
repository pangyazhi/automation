package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class TestInstance extends Model {
    public TestInstance(){
        this.setType("TestInstance");
    }

    @DBRef
    private LinkedList<TestSuite> testSuites = new LinkedList<>();

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

    @DBRef
    private TestEnvironment environment;
    @DBRef
    private LinkedList<TestResult> results;
    @DBRef
    private Task lastTask;
}
