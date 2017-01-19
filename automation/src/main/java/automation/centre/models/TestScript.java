package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public abstract class TestScript extends Model {
    @DBRef
    private LinkedList<Variable> inputVariables = new LinkedList<>();
    @DBRef
    private LinkedList<Variable> outputVariables = new LinkedList<>();
    @DBRef
    private LinkedList<TestScript> subTestScripts = new LinkedList<>();
    private boolean isRerunnable;
    private Enum<HANDLE> handle;

    public LinkedList<Variable> getInputVariables() {
        return inputVariables;
    }

    public void setInputVariables(LinkedList<Variable> inputVariables) {
        this.inputVariables = inputVariables;
    }

    public LinkedList<Variable> getOutputVariables() {
        return outputVariables;
    }

    public void setOutputVariables(LinkedList<Variable> outputVariables) {
        this.outputVariables = outputVariables;
    }

    public LinkedList<TestScript> getSubTestScripts() {
        return subTestScripts;
    }

    public void setSubTestScripts(LinkedList<TestScript> subTestScripts) {
        this.subTestScripts = subTestScripts;
    }

    public Enum<HANDLE> getHandle() {
        return handle;
    }

    public void setHandle(Enum<HANDLE> handle) {
        this.handle = handle;
    }


    public boolean isRerunnable() {
        return isRerunnable;
    }

    public void setRerunnable(boolean rerunnable) {
        isRerunnable = rerunnable;
    }

    public abstract boolean run();

    public boolean recusiveValidate(){
        //TODO check all the test scripts in its child, if this!!! appear again, return false
        // or when call json(), will cause overflow
        return true;
    }
}
