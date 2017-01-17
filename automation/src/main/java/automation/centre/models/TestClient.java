package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class TestClient extends Model {
    public TestClient() {
        super();
        this.setType("TestClient");
    }
}
