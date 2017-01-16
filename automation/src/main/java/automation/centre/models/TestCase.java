package automation.centre.models;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class TestCase extends TestScript {
    public TestCase(){
        this.setType("TestCase");
    }

    private LinkedList<Task> tasks;
    @Override
    public boolean run() {
        return false;
    }
}
