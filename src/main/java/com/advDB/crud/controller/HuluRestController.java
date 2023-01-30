package com.advDB.crud.controller;

import com.advDB.crud.dto.HuluDto;
import com.advDB.crud.dto.HuluUpdateDto;
import com.advDB.crud.model.HuluEntity;
import com.advDB.crud.repository.HuluRepository;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
  private HuluRepository huluRepository;

  /*
   4.	Retrieve all the movies and shows in database.
   */
  @GetMapping("/api")
  public List<HuluEntity> getAllHuluShows() {
    log.info("Fetching all movies and shows.....");
    return huluRepository.findAll();
  }

  /*
   5.	Display the movie and show’s detail using title.
   */
  @GetMapping("/api/{title}")
  public List<HuluEntity> getHuluShowsByTitle(@PathVariable(required = true) String title) {
    log.info("Fetching movies and shows by title : "+title);
    if(StringUtils.isNotBlank(title))
       return huluRepository.findByTitle(title);
    else return Collections.emptyList();
  }

  /*
   3.	Delete the movie and show information using title.
   */
  @DeleteMapping("/api/{title}")
  public void deleteByTitle(@PathVariable(required = true) String title) {
    log.info("Deleting movies and shows by title : "+title);
     huluRepository.deleteByTitle(title);
  }

  /*
    2.Update the movie and show information using title. (By update only id, title, description, score, and rating)
  */
  @PatchMapping("/api/{title}")
  public void updateByTitle(@PathVariable(required = true) String title,@RequestBody(required = true) HuluUpdateDto huluUpdateDto) {
    log.info("Updating id,title,description,score,rating of movies and shows by title : "+huluUpdateDto.getScore());
    HuluEntity huluEntityObject = new HuluEntity();
    BeanUtils.copyProperties(huluUpdateDto,huluEntityObject);
    huluRepository.updateMovieDetails(huluEntityObject,title);
  }

  /*
    1.	Insert the new movie and show.
   */
  @PostMapping("/api")
  public void insertMovieAndShow(@RequestBody HuluDto huluObject) {
    log.info("Inserting Hulu movie and show : "+huluObject.getTitle());
    HuluEntity huluEntityObject = new HuluEntity();
    BeanUtils.copyProperties(huluObject,huluEntityObject);
    huluRepository.insert(huluEntityObject);
  }

}
