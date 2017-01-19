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

    void save(Model model) {
        model.setUpdatedAt(new Date());
        repository.save(model);
    }

    public Model create(Model model) {
        model.setCreatedAt(new Date());
        model.setDisabled(false);
        repository.save(model);
        //below line is important, solved the clone issue
        return repository.findById(model.get_id());
        //return model;
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

    public String findByRegex(String regex) {
        return listToJson(repository.findByRegex(regex));
    }

    public String findByType(String type) {
        return listToJson(repository.findByType(type));
    }

    public String findByName(String name) {
        return listToJson(repository.findByName(name));
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public long count() {
        return repository.count();
    }


    public String findById(String id) {

        Model model = repository.findById(id);
        return model.toJson();
    }
}
