package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class Task extends TestScript {
    private UIObject uiObject;
    private Variable variable;
    private String action;
    public Task() {
        super();
        this.setType("Task");
    }

    @Override
    public boolean run() {
        return false;
    }
}
