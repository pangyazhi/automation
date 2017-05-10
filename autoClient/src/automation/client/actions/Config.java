package automation.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by jien.huang on 20/10/2016.
 */
public class Config {

    private Properties properties = new Properties();
    private static Config instance = new Config();

    private Config(){


        for(File file : new File(".").listFiles())
            if (file.getName().endsWith(".properties")) {
                Properties config = new Properties();
                try {
                    config.load(new FileInputStream(file));
                    properties.putAll(config);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static Config getInstance(){
        return instance;
    }

    public Object get(String key){
        return properties.getProperty(key);
    }

    public Object get(String key, Object defaultValue){
        Object value = get(key);
        if(value==null)
            value = defaultValue;
        return value;
    }
}
