package automation.datacachelayer;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by jien.huang on 08/05/2017.
 */
public class MongoData {
//maybe we need a metadata data later
    private final MongoCollection data;
    Gson gson = new Gson();

    private MongoData(){

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("test");

        data = db.getCollection("data");

    }

    private static MongoData _instance = null;

    public static MongoData get_instance(){
        synchronized (MongoData.class) {
            if (_instance == null) {
                _instance = new MongoData();
            }
        }
        return _instance;
    }

    public String update(String json){
        assert StringUtils.isNotEmpty(json);
        Document dbObject = Document.parse(json);
        String id = (String) dbObject.get("_id");
        assert id!=null;
        assert dbObject.get("type")!=null;
        dbObject.append("updatedAt", new Date());
        BasicDBObject query = new BasicDBObject("_id", id);
        Object doc = data.findOneAndReplace(query, dbObject);
        return findById(id);
    }

    //we only support AND between conditions
    public String find(String ... conditions){
        assert conditions.length > 0;
        List<BasicDBObject> queries = new ArrayList<BasicDBObject>();
        for(String condition: conditions){
            String[] pair = condition.split("=");
            String key = pair[0];
            String value = pair[1];
            //if value contain a regex string, will search as regular expression; Note: case sensitive !
            if (value.contains(".")||value.contains("*")||value.contains("+")||value.contains("|")){
                Pattern pattern = Pattern.compile(value);
                queries.add( new BasicDBObject(key, pattern));
            }else{
                queries.add( new BasicDBObject(key,value));
            }
        }
        MongoCursor it = null;
        if (conditions.length > 1){
            it = data.find(new BasicDBObject("$and", queries)).iterator();
        }else {
            it = data.find(queries.get(0)).iterator();
        }
        ArrayList list = new ArrayList();
        while (it.hasNext()){
            list.add(it.next());
        }
        return gson.toJson(list);
    }

    public String findById(String id){
        assert StringUtils.isNotEmpty(id);
        BasicDBObject query = new BasicDBObject("_id", id);
        return gson.toJson(data.find(query).first());

    }

    public String insert (String json){
        //_id must be provided, type must be provided
        //here, we will provide createdDate
        //and later, will provide versionNo
        assert StringUtils.isNotEmpty(json);
        Document dbObject = Document.parse(json);

        String id = (String) dbObject.get("_id");
        assert id!=null;
        assert dbObject.get("type")!=null;
        dbObject.put("createdAt", new Date());
        data.insertOne(dbObject);
        return findById(id);
    }

    public void delete(String id){
        assert StringUtils.isNotEmpty(id);
        BasicDBObject query = new BasicDBObject("_id", id);
        data.deleteOne(query);
    }

    public void deleteAll(){
        data.drop();
    }
}
