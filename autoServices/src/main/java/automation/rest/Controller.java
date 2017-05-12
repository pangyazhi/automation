package automation.rest;

import automation.datacachelayer.DataCache;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jien.huang on 09/05/2017.
 */
@RestController
public class Controller {

    Gson gson = new Gson();

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

    //PUT update
    @RequestMapping(value= "/update", method = RequestMethod.PUT)
    public @ResponseBody String update(WebRequest webReqest) {
        Map<String, String[]> params = webReqest.getParameterMap();
        return DataCache.get_instance().update(gson.toJson(params));
    }

    //DELETE delete
    @RequestMapping(value= "/delete", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@RequestParam String id){
        DataCache.get_instance().delete(id);
        //TODO ??????
        return "OK";
    }
    //GET get by id
    @RequestMapping(value= "/id", method = RequestMethod.DELETE)
    public @ResponseBody String id(@RequestParam String id){
        return DataCache.get_instance().get(id);
    }
    //GET search ( accept regex )
    @RequestMapping(value= "/search", method = RequestMethod.GET)
    public @ResponseBody String search(WebRequest webReqest){
        Map<String, String[]> params = webReqest.getParameterMap();
        int size = params.size();
        String[] parameters = new String[size];
        int count = 0;
        for(String key: params.keySet()){
            String[] value = params.get(key);
            String realValue = "";
            if(value.length > 1){
                parameters[count] = key + "=(";
                for(int j = 0 ;j <value.length -1; j++){
                    parameters[count] += value[j] + "|";
                }
                parameters[count] = StringUtils.removeEnd(parameters[count],"|");
                parameters[count] += ")";

            }else{
                parameters[count] = key+"="+value[0];
            }
            count ++;
        }

        return DataCache.get_instance().search(parameters);

    }
}
