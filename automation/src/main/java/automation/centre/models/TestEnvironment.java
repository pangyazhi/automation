package automation.centre.models;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class TestEnvironment extends Model {
    private LinkedList<Variable> settings = new LinkedList<>();
    private TestClient host;
    private TestClient client;

    public TestEnvironment() {
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
