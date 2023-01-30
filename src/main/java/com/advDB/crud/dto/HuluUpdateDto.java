package com.advDB.crud.dto;

import lombok.Data;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */
@Data
public class HuluUpdateDto {
  private String title;
  private String description;
  private double score;
  private String rating;
}
