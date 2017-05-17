package automation.rest;

import automation.datacachelayer.DataCache;
import automation.instance.TestInstancesManager;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jien.huang on 09/05/2017.
 */
@RestController
public class Controller {

    final Gson gson = new Gson();

    /**
     * Test if the service still alive
     * @return {message: OK}
     */
    @RequestMapping( value = "/ping", method = RequestMethod.GET )
    public @ResponseBody String ping(){
        HashMap<String, String> ret = new HashMap<>();
        ret.put("message", "OK");
        return gson.toJson(ret);
    }

    //put insert
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestBody String requestBody){
        return DataCache.get_instance().insert(requestBody);
    }

    //PUT update
    @RequestMapping(value= "/update", method = RequestMethod.POST)
    public @ResponseBody String update(@RequestBody String requestBody) {
        return DataCache.get_instance().update(requestBody);
    }


    //DELETE delete
    @RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable String id){
        DataCache.get_instance().delete(id);
        HashMap<String, String> ret = new HashMap<>();
        ret.put("message", "OK");
        return gson.toJson(ret);
    }
    //GET get by id
    @RequestMapping(value= "/id/{id}", method = RequestMethod.GET)
    public @ResponseBody String id(@PathVariable String id){
        return DataCache.get_instance().get(id);
    }
    //GET search ( accept regex )
    @RequestMapping(value= "/search", method = RequestMethod.GET)
    public @ResponseBody String search(WebRequest webRequest){
        Map<String, String[]> params = webRequest.getParameterMap();
        int size = params.size();
        String[] parameters = new String[size];
        int count = 0;
        for(String key: params.keySet()){
            String[] value = params.get(key);
            parameters[count] = key+"="+value[0];
            count ++;
        }
        return DataCache.get_instance().search(parameters);
    }

    //===============================================================
    //create instance by monitor
    @RequestMapping(value = "/instance/create", method = RequestMethod.POST)
    public @ResponseBody String createInstance(@RequestBody String requestBody) {
        return TestInstancesManager.get_instance().create(requestBody);
    }
    //list all instance by monitor
    @RequestMapping(value = "/instance/list", method = RequestMethod.GET)
    public @ResponseBody String list() {
        return TestInstancesManager.get_instance().list();
    }
    //read instance by monitor
    @RequestMapping(value = "/instance/read/{id}", method = RequestMethod.GET)
    public @ResponseBody String read(@PathVariable String id) {
        return TestInstancesManager.get_instance().get(id);
    }
    //get current step by robot client
    @RequestMapping(value = "/instance/current/{id}", method = RequestMethod.GET)
    public @ResponseBody String current(@PathVariable String id) {
        return TestInstancesManager.get_instance().current(id);
    }
    //set current step result by robot client
    @RequestMapping(value = "/instance/result", method = RequestMethod.POST)
    public @ResponseBody String result(@RequestBody String requestBody) {
        return TestInstancesManager.get_instance().result(requestBody);
    }
    //update instance before run, by monitor
    @RequestMapping(value = "/instance/update", method = RequestMethod.POST)
    public @ResponseBody String updateInstance(@RequestBody String requestBody) {
        return TestInstancesManager.get_instance().update(requestBody);
    }
    //start by monitor
    @RequestMapping(value = "/instance/start/{id}", method = RequestMethod.POST)
    public @ResponseBody String start(@PathVariable String id) {
        return TestInstancesManager.get_instance().start(id);
    }
    //stop by monitor
    @RequestMapping(value = "/instance/stop/{id}", method = RequestMethod.POST)
    public @ResponseBody String stop(@PathVariable String id) {
        return TestInstancesManager.get_instance().stop(id);
    }
    //pause by monitor
    @RequestMapping(value = "/instance/pause/{id}", method = RequestMethod.POST)
    public @ResponseBody String pause(@PathVariable String id) {
        return TestInstancesManager.get_instance().pause(id);
    }
    //resume by monitor
    @RequestMapping(value = "/instance/resume/{id}", method = RequestMethod.POST)
    public @ResponseBody String resume(@PathVariable String id) {
        return TestInstancesManager.get_instance().resume(id);
    }
    //finish by itself
    @RequestMapping(value = "/instance/finish/{id}", method = RequestMethod.POST)
    public @ResponseBody String finish(@PathVariable String id) {
        return TestInstancesManager.get_instance().finish(id);
    }
    //delete by monitor
    @RequestMapping(value = "/instance/delete/{id}", method = RequestMethod.POST)
    public @ResponseBody String deleteInstance(@PathVariable String id) {
        return TestInstancesManager.get_instance().delete(id);
    }

}
