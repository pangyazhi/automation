package automation.centre;

import automation.centre.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by jien.huang on 12/01/2017.
 */

@org.springframework.stereotype.Repository
public class Repository  {

    @Autowired
    private MongoTemplate template;

    Model findByName(String name){
        Query q = Query.query(Criteria.where("name").is(name));
        Model model = template.findOne(q, Model.class, "models");
        return model;
    }

    Model findById(String id){
        return template.findById(id, Model.class, "models");
    }

    void save(Model model){
        template.save(model, "models");
        //template.save(model);
    }

    void deleteAll(){
        template.dropCollection("models");
    }

    List<Model> findByRegex(String regex){
        Query q = Query.query(Criteria.where("name").regex(regex).orOperator(Criteria.where("description").regex(regex)));
        return template.find(q, Model.class, "models");
    }

    List<Model> findByRegexWithType(String regex, String type){
        Query q = Query.query(Criteria.where("type").is(type).andOperator(
                Criteria.where("name").regex(regex).orOperator(Criteria.where("description").regex(regex))
        ));
        return template.find(q, Model.class, "models");
    }

    long count(){
        return template.count(new Query().limit(1), Model.class, "models");
    }

//    //@Query(value = "{ 'name' : { '$regex' : regex } , 'description' : {'$regex' : regex}")
//    @Query("{$or: [ {'name' : {'$regex' : ?0 , '$options' : 'i'}}, {'description' : {'$regex' : ?0 , '$options' : 'i' }}]}")
//    List<Model> findByRegex(String regex);
//
//    @Query("{ 'type': {'$regex' : ?1 , '$options' : 'i' }, '$and':[  {$or: [ {'name' : {'$regex' : ?0 , '$options' : 'i'}}, {'description' : {'$regex' : ?0 , '$options' : 'i' }}]}]}")
//    List<Model> findByRegexWithType(String regex, String type);
}
