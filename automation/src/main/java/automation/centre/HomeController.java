package automation.centre;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jien.huang on 13/01/2017.
 */
@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger("Controller");
    Gson gson = new Gson();
    @Autowired
    Repository repository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        logger.info("Check the server status");
        return "{\"message\": \"OK\"}";
    }

    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    public @ResponseBody String findByName(@PathVariable String name){
        return repository.findByName(name).toJson();
    }

    @RequestMapping(value = "/findByRegex/{regex}", method = RequestMethod.GET)
    public @ResponseBody String findByRegex(@PathVariable String regex){
        return RepositoryFactory.getInstance().listToJson(repository.findByRegex(regex));
    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(Exception ex) {

    }
}
