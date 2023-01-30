package com.advDB.crud.service;

import com.advDB.crud.Config.DBSeqGenerator;
import com.advDB.crud.dto.HuluDto;
import com.advDB.crud.dto.HuluUpdateDto;
import com.advDB.crud.model.HuluEntity;
import com.advDB.crud.repository.HuluRepository;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author Sravanti Cherukuri
 * @Date 29-01-2023
 */
@Service
@Log4j2
public class HuluService {
  @Autowired
  private DBSeqGenerator seqGenerator;
  @Autowired
  private HuluRepository huluRepository;
  public List<HuluEntity> insertMovieAndShow(List<HuluDto> huluObjectList){
    List<HuluEntity> huluInsertionList = new ArrayList<>();
    int seq_id = seqGenerator.getNextSequence();
    final int[] counter = {1};
    huluObjectList.stream().forEach(huluDto -> {
      List<HuluEntity> huluEntity = huluRepository.findByTitle(huluDto.getTitle());
      if(CollectionUtils.isEmpty(huluEntity)){
     HuluEntity huluEntityObject = new HuluEntity();
      BeanUtils.copyProperties(huluDto,huluEntityObject);
      huluEntityObject.setId(seq_id+ counter[0]);
      huluInsertionList.add(huluEntityObject);
       counter[0]++;
       }
    });
    return huluRepository.saveAll(huluInsertionList);
  }
  public UpdateResult updateByTitle(HuluUpdateDto huluUpdateDto,String title){
    HuluEntity huluEntityObject = new HuluEntity();
    BeanUtils.copyProperties(huluUpdateDto,huluEntityObject);
   return huluRepository.updateMovieDetails(huluEntityObject,title);
  }
  public String deleteByTitle(String title){
    return  huluRepository.deleteByTitle(title);
  }
  public List<HuluEntity> fetchByTitle(String title){
    return huluRepository.findByTitle(title);
  }
  public List<HuluEntity> fetchAllshows(){
    return huluRepository.findAll();
  }
}
