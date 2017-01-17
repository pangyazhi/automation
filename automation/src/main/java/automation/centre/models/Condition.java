package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class Condition extends TestScript {
    private String condition;

    public Condition() {
        super();
        this.setType("Condition");
    }

    @Override
    public boolean run() {
        return false;
    }
}
