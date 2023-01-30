package com.advDB.crud.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */
@Data
public class HuluDto {

   private String title;
   private int clips_count;
   private String description;
   private int episodes_count;
   private List genres;
   private double score;
   private int seasons_count;
   private String company;
   private Date released_at;
   private String rating;

}
