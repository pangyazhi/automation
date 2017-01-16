package automation.centre.models;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jien.huang on 12/01/2017.
 */
@EnableAutoConfiguration
public abstract class Model {


    static Logger logger = LoggerFactory.getLogger("Models");


    @Id
    private String _id;
    private String name;
    private String description;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private String type;
    private boolean isDisabled;
    private Date updatedAt;
    private String updatedBy;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Model fromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Model.class);
    }


}
