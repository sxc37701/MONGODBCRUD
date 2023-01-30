package com.advDB.crud.controller;

import com.advDB.crud.dto.HuluDto;
import com.advDB.crud.dto.HuluUpdateDto;
import com.advDB.crud.model.HuluEntity;
import com.advDB.crud.service.HuluService;
import com.mongodb.client.result.UpdateResult;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sravanti Cherukuri
 * @Date 28-01-2023
 */

@Log4j2
@RestController
public class HuluRestController {
  @Autowired
  private HuluService huluService;
  /*
   4.	Retrieve all the movies and shows in database.
   */
  @GetMapping("/api")
  public ResponseEntity getAllHuluShows() {
    log.info("Fetching all movies and shows.....");
    try{
    return new ResponseEntity(huluService.fetchAllshows(), HttpStatus.OK);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /*
   5.	Display the movie and show’s detail using title.
   */
  @GetMapping("/api/{title}")
  public ResponseEntity getHuluShowsByTitle(@PathVariable(required = true) String title) {
    log.info("Fetching movies and shows by title : "+title);
    try{
    if(StringUtils.isNotBlank(title))
       return new ResponseEntity(huluService.fetchByTitle(title),HttpStatus.OK);
    else return new ResponseEntity<>(Collections.emptyList(),HttpStatus.NO_CONTENT);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /*
   3.	Delete the movie and show information using title.
   */
  @DeleteMapping("/api/{title}")
  public ResponseEntity deleteByTitle(@PathVariable(required = true) String title) {
    log.info("Deleting movies and shows by title : "+title);
    try{
        String delStatus = huluService.deleteByTitle(title);
        return new ResponseEntity<>(delStatus,HttpStatus.OK);
       }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /*
    2.Update the movie and show information using title. (By update only id, title, description, score, and rating)
  */
  @PatchMapping("/api/{title}")
  public ResponseEntity updateByTitle(@PathVariable(required = true) String title,@RequestBody(required = true) HuluUpdateDto huluUpdateDto) {
    log.info("Updating id,title,description,score,rating of movies and shows by title : "+huluUpdateDto.getScore());
    try{
      if(!ObjectUtils.isEmpty(huluUpdateDto) && StringUtils.isNotBlank(title)){
        UpdateResult updateResult = huluService.updateByTitle(huluUpdateDto,title);
        if(updateResult.getModifiedCount() == 0)
          return new ResponseEntity<>("No document found to update",HttpStatus.OK);
          else
        return new ResponseEntity<>("Hulu details updated successfully...",HttpStatus.OK);
        }
      else
        return new ResponseEntity<>("Error occurred while updating...",HttpStatus.CONFLICT);
        }catch (Exception exc){
      return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
    }
  }

  /*
    1.	Insert the new movie and show.
   */
  @PostMapping("/api")
  public ResponseEntity insertMovieAndShow(@RequestBody List<HuluDto> huluObjectList) {
    try{
    if(!CollectionUtils.isEmpty(huluObjectList)){
      log.info("Inserting Hulu movie and show : "+huluObjectList.size());
      try{
        List<HuluEntity> huluInsertionList_result = huluService.insertMovieAndShow(huluObjectList);
        if(!CollectionUtils.isEmpty(huluInsertionList_result) && huluInsertionList_result.size()>0)
          return new ResponseEntity("Hulu movie/shows inserted successfully",HttpStatus.OK);
        else
          return new ResponseEntity("No documents inserted",HttpStatus.OK);
          }catch(Exception exc){
        return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
      }
    }else
      return new ResponseEntity<>("Empty hulu data cannot be inserted...",HttpStatus.BAD_REQUEST);
     }catch(Exception exc){
      return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
    }
  }
}
