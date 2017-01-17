package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class TestResult extends Model {
    @DBRef
    private Task task;
    private String respone;
    public TestResult() {
        super();
        this.setType("TestResult");
    }
}
