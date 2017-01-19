package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class Condition extends TestScript {
    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public TestScript getOnYes() {
        return onYes;
    }

    public void setOnYes(TestScript onYes) {
        this.onYes = onYes;
    }

    public TestScript getOnNo() {
        return onNo;
    }

    public void setOnNo(TestScript onNo) {
        this.onNo = onNo;
    }

    @DBRef
    private TestScript onYes;
    @DBRef
    private TestScript onNo;

    public Condition() {
        super();
        this.setType("Condition");
    }

    @Override
    public boolean run() {
        return false;
    }
}
