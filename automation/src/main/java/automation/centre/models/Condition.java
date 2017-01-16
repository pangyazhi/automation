package automation.centre.models;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class Condition extends TestScript {
    public Condition(){
        this.setType("Condition");
    }
    private String condition;
    @Override
    public boolean run() {
        return false;
    }
}
