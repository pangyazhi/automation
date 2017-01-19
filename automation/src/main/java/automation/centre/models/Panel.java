package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
@Document(collection = "models")
public class Panel extends Model {
    public LinkedList<UIObject> getUiObjects() {
        return uiObjects;
    }

    public void setUiObjects(LinkedList<UIObject> uiObjects) {
        this.uiObjects = uiObjects;
    }

    @DBRef
    private LinkedList<UIObject> uiObjects = new LinkedList<>();

    public Panel() {
        super();
        this.setType("Panel");
    }

    public void addUIObject(UIObject uiObject){
        uiObjects.add(uiObject);
    }
}
