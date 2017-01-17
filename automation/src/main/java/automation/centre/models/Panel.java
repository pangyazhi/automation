package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class Panel extends Model {
    @DBRef
    private LinkedList<UIObject> uiObjects;

    public Panel() {
        super();
        this.setType("Panel");
    }
}
