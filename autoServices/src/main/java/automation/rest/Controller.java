package automation.rest;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by jien.huang on 09/05/2017.
 */
@RestController
public class Controller {

    Gson gson = new Gson();
    @RequestMapping("/ping")
    public @ResponseBody String ping(){
        HashMap<String, String> ret = new HashMap<>();
        ret.put("message", "OK");
        return gson.toJson(ret);
    }
}
