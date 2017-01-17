package automation.centre;

import automation.centre.models.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by jien.huang on 12/01/2017.
 */

@RepositoryRestResource
public interface Repository extends MongoRepository<Model, String> {

    Model findByName(String name);

    //@Query(value = "{ 'name' : { '$regex' : regex } , 'description' : {'$regex' : regex}")
    @Query("{$or: [ {'name' : {'$regex' : ?0 , '$options' : 'i'}}, {'description' : {'$regex' : ?0 , '$options' : 'i' }}]}")
    List<Model> findByRegex(String regex);

    @Query("{ 'type': {'$regex' : ?1 , '$options' : 'i' }, '$and':[  {$or: [ {'name' : {'$regex' : ?0 , '$options' : 'i'}}, {'description' : {'$regex' : ?0 , '$options' : 'i' }}]}]}")
    List<Model> findByRegexWithType(String regex, String type);
}
