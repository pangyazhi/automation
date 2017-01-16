package automation.centre.models;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * Created by jien.huang on 12/01/2017.
 */
@CompoundIndex(name="type_name_idx", def="{'type':1, 'name':1}")
public abstract class Model implements Cloneable {


    static Logger logger = LoggerFactory.getLogger("Models");


    @Id
    private String _id;
    @Indexed
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
    @Indexed
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private String createdBy;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Object fromJson(String jsonString, Class klass) {
        Gson gson = new Gson();

        return gson.fromJson(jsonString, klass);
    }


    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
