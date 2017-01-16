package automation.centre.models;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class Task extends TestScript {
    private UIObject uiObject;
    private Variable variable;
    private String action;
    public Task() {
        this.setType("Task");
    }

    @Override
    public boolean run() {
        return false;
    }
}
