package com.advDB.crud.repository;

import com.advDB.crud.model.HuluEntity;
import com.mongodb.client.result.UpdateResult;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */

public interface CustomHuluRepository {

  public UpdateResult updateMovieDetails(HuluEntity huluEntity,String title);
  public String deleteByTitle(String title);

}
