package automation.centre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jien.huang on 13/01/2017.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger("Controller");
    //Gson gson = new Gson();

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "{\"message\": \"OK\"}";
    }
}
