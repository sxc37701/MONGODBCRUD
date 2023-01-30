package com.advDB.crud.Config;

import com.advDB.crud.model.HuluEntity;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @author Sravanti Cherukuri
 * @Date 29-01-2023
 */

@Service
public class DBSeqGenerator {
  @Autowired
  private MongoTemplate mongoTemplate;

  public int getNextSequence() {
    final Query q = new Query();
    q.limit(1);
    q.with(Sort.by(Direction.DESC,"id"));
    HuluEntity entityObj = mongoTemplate.findOne(q,HuluEntity.class);

      return !Objects.isNull(entityObj) ? entityObj.getId() : 1;
  }
}
