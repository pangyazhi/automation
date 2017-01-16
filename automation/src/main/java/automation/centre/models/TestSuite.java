package automation.centre.models;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 12/01/2017.
 */
@Document
@EnableAutoConfiguration
public class TestSuite extends Model {

    public TestSuite(){
        this.setType("TestSuite");
    }
}
