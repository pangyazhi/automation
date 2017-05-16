package automation.datacachelayer;

import com.google.gson.Gson;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jien.huang on 09/05/2017.
 */
public class DataCache {
    private static DataCache _instance;
    private static CacheAccess<String, String> instanceCache;
    private static CacheAccess<String, String> searchCache;
    private Gson gson = new Gson();

    private DataCache(){
        MongoData.get_instance();
        //CompositeCacheManager ccm = CompositeCacheManager.getUnconfiguredInstance();
        Properties props = new Properties();

        props.put("jcs.default","");
        props.put("jcs.default.cacheattributes",
                "org.apache.commons.jcs.engine.CompositeCacheAttributes");
        props.put("jcs.default.cacheattributes.ShrinkerIntervalSeconds",60);
        props.put("jcs.default.cacheattributes.MaxObjects",1000);
                props.put("jcs.default.cacheattributes.MemoryCacheName",
                "org.apache.commons.jcs.engine.memory.lru.LRUMemoryCache");
// lots more props.put - this is basically the contents of cache.ccf

        JCS.setConfigProperties(props);
        instanceCache = JCS.getInstance("instanceCache");
        searchCache = JCS.getInstance("searchCache");
    }

    public static DataCache get_instance(){
        synchronized (DataCache.class) {
            if (_instance == null)
                _instance = new DataCache();
        }
        return _instance;
    }

    public String search(String... conditions){
        assert conditions.length > 0;
        String result = MongoData.get_instance().find(conditions);
        List<Map> idList = gson.fromJson(result, List.class);
        String key = ArrayUtils.toString(conditions);
        String retList = "[";
        for(Map id: idList){
            retList +="{'"+id.get("_id")+"'},";
            //this get put things into cache
            get(id.get("_id").toString());
        }
        if (retList.length()>1)
            retList = StringUtils.removeEnd(retList, ",");
        retList += "]";

        searchCache.putSafe(key, retList);
        return retList;
    }

    public String get(String id){
        String instance = instanceCache.get(id);
        if (StringUtils.isNotEmpty(instance) && !instance.equalsIgnoreCase("null"))
            return instance;
        instance = MongoData.get_instance().findById(id);
        if (StringUtils.isNotEmpty(instance) && !instance.equalsIgnoreCase("null")){
            //very hard to reproduce this situation, so leave it uncovered by unit test.
            instanceCache.put(id, instance);
        }
        if (instance.equalsIgnoreCase("null"))
            instance = null;
        return instance;
    }

    public String update(String json){
        assert StringUtils.isNotEmpty(json);
        String result = MongoData.get_instance().update(json);
        String id = getId(result);
        instanceCache.put(id, result);
        return result;
    }

    public String insert(String json){
        assert StringUtils.isNotEmpty(json);
        String result = MongoData.get_instance().insert(json);
        String id = getId(result);
        instanceCache.putSafe(id, result);
        return result;
    }

    public void delete(String id){
        assert StringUtils.isNotEmpty(id);
        MongoData.get_instance().delete(id);
        instanceCache.remove(id);
    }

    public void deleteAll(){
        instanceCache.clear();
        searchCache.clear();
        MongoData.get_instance().deleteAll();
    }

    private String getId(String result) {
        HashMap identifiedable =  gson.fromJson(result, HashMap.class);
        return (String) identifiedable.get("_id");
    }
}
