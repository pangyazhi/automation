package automation.datacachelayer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by jien.huang on 08/05/2017.
 */
public class MongoData {
//maybe we need a metadata collection later
    private final MongoCollection collection;

    private MongoData(){

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("test");

        collection = db.getCollection("data");
    }

    private static MongoData _instance = null;

    public static MongoData get_instance(){
        if (_instance == null){
            _instance = new MongoData();
        }
        return _instance;
    }

    public void insert (String json){

        assert json.contains("id");
    }
}
