package automation.centre.models;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class Condition extends TestScript {
    private String condition;

    public Condition() {
        this.setType("Condition");
    }

    @Override
    public boolean run() {
        return false;
    }
}
