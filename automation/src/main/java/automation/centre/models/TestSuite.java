package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 12/01/2017.
 */
@Document(collection = "models")
public class TestSuite extends TestScript {

    public TestSuite() {
        super();
        this.setType("TestSuite");
    }

    @Override
    public boolean run() {
        return false;
    }
}
