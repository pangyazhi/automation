package automation.centre.models;

/**
 * Created by jien.huang on 16/01/2017.
 */
public class Variable extends Model{
    public Variable(){
        this.setType("Variable");
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    private Object value;
    private boolean isRequired;
}
