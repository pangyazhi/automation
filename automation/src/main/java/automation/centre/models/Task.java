package automation.centre.models;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class Task extends TestScript {
    public Task(){
        this.setType("Task");
    }
    private UIObject uiObject;
    private Variable variable;
    private String action;

    @Override
    public boolean run() {
        return false;
    }
}
