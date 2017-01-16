package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class TestResult extends Model {
    @DBRef
    private Task task;
    private String respone;
    public TestResult() {
        this.setType("TestResult");
    }
}
