package automation.centre.models;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 12/01/2017.
 */
@Document
@EnableAutoConfiguration
public class Project extends Model {
    private String version;
    private String build;

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

    public Project(){
        this.setType("Project");
    }
}
