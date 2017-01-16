package automation.centre.models;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class TestCase extends TestScript {
    private LinkedList<Task> tasks;

    public TestCase() {
        this.setType("TestCase");
    }

    @Override
    public boolean run() {
        return false;
    }
}
