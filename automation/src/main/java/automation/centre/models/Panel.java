package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class Panel extends Model {
    public Panel(){
        this.setType("Panel");
    }
    @DBRef
    private LinkedList<UIObject> uiObjects;
}
