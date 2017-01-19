package automation.centre.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jien.huang on 12/01/2017.
 */
@CompoundIndex(name = "type_name_idx", def = "{'type':1, 'name':1}")
public abstract class Model implements Cloneable {

    public Model(){
        this.set_id(UUID.randomUUID().toString());
    }

    static Logger logger = LoggerFactory.getLogger("Models");


    @Id
    private String _id;
    @Indexed
    private String name;
    private String description;
    private String type;
    private boolean isDisabled;
    @Indexed
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;

    public static Object fromJson(String jsonString, Class klass) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, klass);
    }

    public String get_id() {
        return _id;
    }

    public final void set_id(String _id) {
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

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
