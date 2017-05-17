package automation.instance;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * Created by jien.huang on 16/05/2017.
 */
public class TestInstancesManager {
    Gson gson = new Gson();
    private static TestInstancesManager _instance;
    private HashMap<String, TestInstance> instanceHashMap = new HashMap<String, TestInstance>();
    private TestInstancesManager(){

    }
    public static TestInstancesManager get_instance(){
        synchronized (TestInstancesManager.class){
            if(_instance==null)
                _instance = new TestInstancesManager();
        }
        return _instance;
    }

    public String create(String requestBody) {

        TestInstance testInstance = gson.fromJson(requestBody, TestInstance.class);
        instanceHashMap.put(testInstance._id,testInstance);
        return "OK";
    }

    public String update(String requestBody) {

        TestInstance testInstance = gson.fromJson(requestBody, TestInstance.class);
        String id = testInstance._id;
        if (testInstance.status!= Status.IDLE)
            return "Already Started";
        instanceHashMap.put(testInstance._id,testInstance);
        return "OK";
    }

    public String list() {
        return gson.toJson(instanceHashMap);
    }

    public String get(String id) {
        return gson.toJson(instanceHashMap.get(id));
    }

    public String current(String id) {
        return instanceHashMap.get(id).currentSteps;
    }

    public String result(String requestBody) {
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        String id = jsonObject.get("_id").getAsString();
        instanceHashMap.get(id).setResult(requestBody);
        return "OK";
    }

    public String start(String id) {
        TestInstance testInstance = instanceHashMap.get(id);
        testInstance.start();
        return "OK";
    }

    public String stop(String id) {
        TestInstance testInstance = instanceHashMap.get(id);
        testInstance.stop();
        return "OK";
    }

    public String pause(String id) {
        TestInstance testInstance = instanceHashMap.get(id);
        testInstance.pause();
        return "OK";
    }

    public String resume(String id) {
        TestInstance testInstance = instanceHashMap.get(id);
        testInstance.resume();
        return "OK";
    }

    public String finish(String id) {
        TestInstance testInstance = instanceHashMap.get(id);
        testInstance.finish();
        return "OK";
    }

    public String delete(String id) {
        instanceHashMap.remove(id);
        return "OK";
    }
}
