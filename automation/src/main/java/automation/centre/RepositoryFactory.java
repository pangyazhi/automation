package automation.centre;

import automation.centre.models.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jien.huang on 13/01/2017.
 */
public class RepositoryFactory {
    private static RepositoryFactory _instance;
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
        return repository.save(model);
    }

    public Model create(Model model) {
        model.setCreatedAt(new Date());
        model.setDisabled(false);
        return save(model);
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

    private void copyValues(Model model, Model newModel, Field field) {
        try {
            Object value = field.get(model);
            Field newModelField = newModel.getClass().getField(field.getName());
            newModelField.setAccessible(true);
            newModelField.set(newModel, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void delete(Model model) {
        repository.delete(model);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public long count() {
        return repository.count();
    }
}
