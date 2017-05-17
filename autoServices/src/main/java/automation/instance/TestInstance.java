package automation.instance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

/**
 * Created by jien.huang on 16/05/2017.
 */

enum Status {
    IDLE, RUNNING, PAUSED, STOPPED, FINISHED
        }

public class TestInstance {
    Gson gson = new Gson();
    String _id;
    Status status = Status.IDLE;
    String rootSuite;
    String currentSteps;
    JsonArray logs = new JsonArray();

    public TestInstance() {

    }

    public void start(){
        this.status = Status.RUNNING;
    }

    public String toJson(){
        return gson.toJson(this);
    }

    public void stop(){
        this.status = Status.STOPPED;
    }

    public void pause(){
        this.status = Status.PAUSED;
    }

    public void resume(){
        this.status = Status.RUNNING;
    }

    public String getCurrentStep() {
        synchronized (TestInstance.class){
            return currentSteps;
        }
    }

    protected void setCurrentSteps(String currentSteps){
        synchronized (TestInstance.class){
            this.currentSteps = currentSteps;
        }
    }

    public void setResult(String result){
        synchronized (TestInstance.class){
            currentSteps = null;
            logs.add(result);
        }
    }

    public void finish() {
        this.status = Status.FINISHED;
    }
}


