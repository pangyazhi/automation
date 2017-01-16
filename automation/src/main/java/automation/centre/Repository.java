package automation.centre;

import automation.centre.models.Model;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by jien.huang on 12/01/2017.
 */
@EnableAutoConfiguration
@org.springframework.stereotype.Repository
public interface Repository extends MongoRepository<Model, String> {
    Model findByName(String name);
    //@Query(value = "{ 'name' : { '$regex' : regex } , 'description' : {'$regex' : regex}")
    @Query("{$or: [ {'name' : {'$regex' : ?0 , '$options' : 'i'}}, {'description' : {'$regex' : ?0 , '$options' : 'i' }}]}")
    List<Model> findByRegex(String regex);
    @Query("{ 'type': {'$regex' : ?1 , '$options' : 'i' }, '$and':[  {$or: [ {'name' : {'$regex' : ?0 , '$options' : 'i'}}, {'description' : {'$regex' : ?0 , '$options' : 'i' }}]}]}")
    List<Model> findByRegexWithType(String regex, String type);
}
