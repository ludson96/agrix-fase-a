package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmService {
  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public Optional<Farm> findFarmById(Long id) {
    return farmRepository.findById(id);
  }

  public List<Farm> findAllFarm() {
    return farmRepository.findAll();
  }

//  public Optional<Farm> updateFarm(Farm farm, Long id) {
//    Optional<Farm> optionalFarm = farmRepository.findById(id);
//
//    if (optionalFarm.isEmpty()) {
//
//    }
//  }
}
