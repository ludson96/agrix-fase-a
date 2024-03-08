package com.betrybe.agrix.services;

import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FarmService {
  private final FarmRepository farmRepository;

  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
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

  public Crop insertCrop(Long farmId, Crop crop) throws CustomError {
    Farm farm = this.findFarmById(farmId);

    Crop newCrop = cropRepository.save(crop);
    newCrop.setFarmId(farm);
    farm.getCrops().add(newCrop);
    farmRepository.save(farm);
    return cropRepository.save(newCrop);
  }

  public List<Crop> findAllCrop(Long farmId) throws CustomError {
    this.findFarmById(farmId);
    return cropRepository.findAll();
  }
}
