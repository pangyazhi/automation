package automation.client.actions;




import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by jien.huang on 19/10/2016.
 */
public class RestClient {
    private static RestClient _instance ;
    private WebTarget target;
    Client client;
    private RestClient(){
        client = ClientBuilder.newClient();
    }

    public static RestClient getInstacne(){
        if(_instance==null)
            _instance = new RestClient();
        return _instance;
    }

    public String get(String path){
        return target.path(path).request().get(String.class);
    }

    public String put(String path, Object input){
        return target.path(path).request().put(Entity.entity(input, MediaType.APPLICATION_JSON)).toString();
    }

}
