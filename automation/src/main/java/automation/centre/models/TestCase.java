package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class TestCase extends TestScript {
    private LinkedList<Task> tasks;

    public TestCase() {
        super();
        this.setType("TestCase");
    }

    @Override
    public boolean run() {
        return false;
    }
}
