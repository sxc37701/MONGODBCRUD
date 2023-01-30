package com.advDB.crud.repository;

import com.advDB.crud.model.HuluEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */


@Repository
public interface HuluRepository extends MongoRepository<HuluEntity, String>,CustomHuluRepository  {
  @Query(value ="{title: ?0}")
  List<HuluEntity> findByTitle(String title);
}
