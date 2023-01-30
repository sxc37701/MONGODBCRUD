package com.advDB.crud.repository;

import com.advDB.crud.model.HuluEntity;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */

public interface CustomHuluRepository {

  public void updateMovieDetails(HuluEntity huluEntity,String title);
  public void deleteByTitle(String title);

}
