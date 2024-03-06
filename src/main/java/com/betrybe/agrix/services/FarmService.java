package com.betrybe.agrix.services;

import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  public Farm findFarmById(Long id) throws CustomError {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new CustomError(
          "Fazenda não encontrada!",
          HttpStatus.NOT_FOUND.value()
      );
    }
      return optionalFarm.get();
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
