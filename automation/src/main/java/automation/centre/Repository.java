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
class Repository {

    private static final String MODELS = "models";
    @Autowired
    private MongoTemplate template;

    List<Model> findByName(String name) {
        Query q = Query.query(Criteria.where("name").is(name));
        return template.find(q, Model.class, MODELS);
    }

    List<Model> findByType(String type) {
        Query q = Query.query(Criteria.where("type").is(type));
        return template.find(q, Model.class, MODELS);
    }

    Model findById(String id){
        return template.findById(id, Model.class, MODELS);
    }

    void save(Model model){
        template.save(model, MODELS);
    }

    void deleteAll(){
        template.dropCollection(MODELS);
    }

    List<Model> findByRegex(String regex){
        Query q = Query.query(Criteria.where("name").regex(regex).orOperator(Criteria.where("description").regex(regex)));
        return template.find(q, Model.class, MODELS);
    }

    List<Model> findByRegexWithType(String regex, String type){
        Query q = Query.query(Criteria.where("type").is(type).andOperator(
                Criteria.where("name").regex(regex).orOperator(Criteria.where("description").regex(regex))
        ));
        return template.find(q, Model.class, MODELS);
    }

    List<Model> findAll(){
        return template.findAll(Model.class, MODELS);
    }

    long count(){
        return template.count(new Query().limit(1), Model.class, MODELS);
    }

}
