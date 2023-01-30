package com.advDB.crud.repository;

import com.advDB.crud.model.HuluEntity;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.UpdateRequest;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;


/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */

public class HuluRepositoryImpl implements CustomHuluRepository {
  @Autowired
  private MongoTemplate mongoTemplate;

  public UpdateResult updateMovieDetails(HuluEntity huluEntity, String title){

    Query query= new Query();
    query.addCriteria(Criteria.where("title").is(title));

    Update update = new Update();
    update.set("title", huluEntity.getTitle());
    update.set("description", huluEntity.getDescription());
    update.set("score",huluEntity.getScore());
    update.set("rating",huluEntity.getRating());
    return  mongoTemplate.updateFirst(query, update, HuluEntity.class);
  }

  public String  deleteByTitle(String title){
    Query query= new Query();
    query.addCriteria(Criteria.where("title").is(title));
     List<HuluEntity> huluEntity=mongoTemplate.findAllAndRemove(query, HuluEntity.class);
     if(!CollectionUtils.isEmpty(huluEntity))
     return "Deleted successfully";
     else
     return "No Document found to delete";
  }
}
