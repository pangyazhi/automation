package automation.centre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jien.huang on 13/01/2017.
 */
@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger("Controller");

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        logger.info("Check the server status");
        return "{\"message\": \"OK\"}";
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public
    @ResponseBody
    String findAll() {
        return RepositoryFactory.getInstance().findAll();
    }


    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    public @ResponseBody String findByName(@PathVariable String name){
        return RepositoryFactory.getInstance().findByName(name);
    }

    @RequestMapping(value = "/findByRegex/{regex}", method = RequestMethod.GET)
    public @ResponseBody String findByRegex(@PathVariable String regex){
        return RepositoryFactory.getInstance().findByRegex(regex);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    String findById(@PathVariable String id) {
        return RepositoryFactory.getInstance().findById(id);
    }

    @RequestMapping(value = "/findByType/{type}", method = RequestMethod.GET)
    public
    @ResponseBody
    String findByType(@PathVariable String type) {
        return RepositoryFactory.getInstance().findByType(type);
    }

    @RequestMapping(value = "/findByRegexWithType/{type}/{regex}", method = RequestMethod.GET)
    public
    @ResponseBody
    String findByRegexWithType(@PathVariable String regex, @PathVariable String type ) {
        return RepositoryFactory.getInstance().findByRegexWithType(regex, type);
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public void handleTodoNotFound(Exception ex) {
//
//    }
}
