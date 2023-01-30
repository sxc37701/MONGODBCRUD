package com.advDB.crud.repository;

import com.advDB.crud.model.HuluEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */

public class HuluRepositoryImpl implements CustomHuluRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  public void updateMovieDetails(HuluEntity huluEntity, String title){

    Query query= new Query();
    query.addCriteria(Criteria.where("title").is(title));

    Update update = new Update();
    //update.set("id", huluUpdateObject.getId());
    update.set("description", huluEntity.getDescription());
    update.set("score",huluEntity.getScore());
    update.set("rating",huluEntity.getRating());
    mongoTemplate.updateFirst(query, update, HuluEntity.class);
  }

  public void deleteByTitle(String title){
    Query query= new Query();
    query.addCriteria(Criteria.where("title").is(title));
     mongoTemplate.findAndRemove(query, HuluEntity.class);
  }
}
