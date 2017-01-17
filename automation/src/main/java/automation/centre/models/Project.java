package automation.centre.models;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jien.huang on 12/01/2017.
 */
@Document
@EnableAutoConfiguration
public class Project extends Model {
    private String version;
    private String build;

    public List<TestSuite> getSuites() {
        return suites;
    }

    public void setSuites(LinkedList<TestSuite> suites) {
        this.suites = suites;
    }

    @DBRef
    private List<TestSuite> suites = new ArrayList<TestSuite>();

    public Project() {
        this.setType("Project");
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public void addSuite(TestSuite suite) {
        this.suites.add(suite);
    }

}
