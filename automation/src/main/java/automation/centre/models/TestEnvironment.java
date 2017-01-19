package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class TestEnvironment extends Model {
    @DBRef
    private LinkedList<Variable> settings = new LinkedList<>();
    @DBRef
    private TestClient host;
    @DBRef
    private TestClient client;

    public TestEnvironment() {
        super();
        this.setType("TestEnvironment");
    }

    public LinkedList<Variable> getSettings() {
        return settings;
    }

    public void setSettings(LinkedList<Variable> settings) {
        this.settings = settings;
    }

    public TestClient getHost() {
        return host;
    }

    public void setHost(TestClient host) {
        this.host = host;
    }

    public TestClient getClient() {
        return client;
    }

    public void setClient(TestClient client) {
        this.client = client;
    }

}
