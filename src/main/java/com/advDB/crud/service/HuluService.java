package com.advDB.crud.service;

import com.advDB.crud.Config.DBSeqGenerator;
import com.advDB.crud.dto.HuluDto;
import com.advDB.crud.dto.HuluUpdateDto;
import com.advDB.crud.model.HuluEntity;
import com.advDB.crud.repository.HuluRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public void insertMovieAndShow(List<HuluDto> huluObjectList){
    List<HuluEntity> huluInsertionList = new ArrayList<>();
    int seq_id = seqGenerator.getNextSequence();
    final int[] counter = {1};
    huluObjectList.stream().forEach(huluDto -> {
     HuluEntity huluEntityObject = new HuluEntity();
      BeanUtils.copyProperties(huluDto,huluEntityObject);
      huluEntityObject.setId(seq_id+ counter[0]);
      huluInsertionList.add(huluEntityObject);
       counter[0]++;
    });
    huluRepository.saveAll(huluInsertionList);
  }
  public void updateByTitle(HuluUpdateDto huluUpdateDto,String title){
    HuluEntity huluEntityObject = new HuluEntity();
    BeanUtils.copyProperties(huluUpdateDto,huluEntityObject);
    huluRepository.updateMovieDetails(huluEntityObject,title);
  }
  public void deleteByTitle(String title){
    huluRepository.deleteByTitle(title);
  }
  public List<HuluEntity> fetchByTitle(String title){
    return huluRepository.findByTitle(title);
  }
  public List<HuluEntity> fetchAllshows(){
    return huluRepository.findAll();
  }
}
