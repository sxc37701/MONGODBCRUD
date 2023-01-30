package com.advDB.crud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Hulu")
public class HuluEntity implements Serializable {

  @Id
  private String _id;

  private int id;

  private String title;

  private int clips_count;

  private String description;

  private int episodes_count ;

  private double score;

  private int seasons_count;

  private String company;

  private String rating;

  private Date released_at;

  private List genres;
}
