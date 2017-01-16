package automation.centre.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.LinkedList;

/**
 * Created by jien.huang on 16/01/2017.
 */
public abstract class TestScript extends Model {
    @DBRef
    private LinkedList<InputVariable> inputVariables = new LinkedList<>();
    @DBRef
    private LinkedList<OutputVariable> outputVariables = new LinkedList<>();
    @DBRef
    private LinkedList<TestScript> subTestScripts = new LinkedList<>();
    private boolean isRerunnable;
    private Enum<HANDLE> handle;

    public LinkedList<InputVariable> getInputVariables() {
        return inputVariables;
    }

    public void setInputVariables(LinkedList<InputVariable> inputVariables) {
        this.inputVariables = inputVariables;
    }

    public LinkedList<OutputVariable> getOutputVariables() {
        return outputVariables;
    }

    public void setOutputVariables(LinkedList<OutputVariable> outputVariables) {
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

    public void addInputVariable(InputVariable inputVariable) {
        this.inputVariables.add(inputVariable);
    }

    public void addOutputVariable(OutputVariable outputVariable) {

    }

    public boolean isRerunnable() {
        return isRerunnable;
    }

    public void setRerunnable(boolean rerunnable) {
        isRerunnable = rerunnable;
    }

    public abstract boolean run();
}
