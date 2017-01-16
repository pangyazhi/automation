package automation.centre;

import automation.centre.models.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by jien.huang on 13/01/2017.
 */
public class RepositoryFactory {
    @Autowired
    private Repository repository;

    private static RepositoryFactory _instance ;
    private RepositoryFactory(){

    }

    public static RepositoryFactory getInstance(){
        if(_instance == null)
            _instance = new RepositoryFactory();
        return _instance;
    }

    public void setRepository(Repository repository){
        this.repository = repository;
    }

    public Model save(Model model){
        model.setUpdatedAt(new Date());
        return repository.save(model);
    }

    public void delete(Model model){
        repository.delete(model);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public long count() {
        return repository.count();
    }
}
