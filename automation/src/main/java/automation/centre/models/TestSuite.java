package automation.centre.models;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 12/01/2017.
 */
@Document
@EnableAutoConfiguration
public class TestSuite extends TestScript {

    public TestSuite() {
        this.setType("TestSuite");
    }

    @Override
    public boolean run() {
        return false;
    }
}
