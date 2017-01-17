package automation.centre;

import automation.centre.models.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by jien.huang on 13/01/2017.
 */

public class RepositoryFactory {
    private static RepositoryFactory _instance;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private Repository repository;

    private RepositoryFactory() {

    }

    public static RepositoryFactory getInstance() {
        if (_instance == null)
            _instance = new RepositoryFactory();
        return _instance;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    protected Model save(Model model) {
        model.setUpdatedAt(new Date());
        repository.save(model);
        return model;
    }

    public Model create(Model model) {
        model.set_id(UUID.randomUUID().toString());
        model.setCreatedAt(new Date());
        model.setDisabled(false);
        repository.save(model);
        return model;
    }

    public void update(Model model) {
        save(model);
    }

    //shallow clone
    public Model clone(Model model) {
        Model newModel = null;
        try {
            newModel = (Model) model.clone();
            newModel.set_id(UUID.randomUUID().toString());
            newModel.setName("Cloned " + model.getName());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return create(newModel);
    }

    public String listToJson(List<Model> modelList){
        return gson.toJson(modelList);
    }



    public void deleteAll() {
        repository.deleteAll();
    }

    public long count() {
        return repository.count();
    }

    public String getByName(String name){
        return repository.findByName(name).toJson();
    }

    public String getById(String id){

        Model model = repository.findById(id);
        return model.toJson();
    }
}
