package automation.rest;

import automation.datacachelayer.DataCache;
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
}
