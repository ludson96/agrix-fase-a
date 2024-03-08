package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDTO;
import com.betrybe.agrix.controllers.dto.CropDTOToEntity;
import com.betrybe.agrix.controllers.dto.FarmDTO;
import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  public ResponseEntity<Farm> insertFarm(@RequestBody FarmDTO farmDTO) {
    Farm newFarm = farmService.insertFarm(farmDTO.dtoToEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  @GetMapping
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> allFarms = farmService.findAllFarm();
    return ResponseEntity.ok().body(allFarms);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable("id") Long id) throws CustomError {
    Farm optionalFarm = farmService.findFarmById(id);
    return ResponseEntity
        .ok()
        .body(optionalFarm);
  }

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDTO> insertCrop(
      @PathVariable(name = "farmId") Long farmId,
      @RequestBody CropDTOToEntity crop
  ) throws CustomError {
    Crop newCrop = farmService.insertCrop(farmId, crop.dtoToEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDTO.fromEntityToDto(newCrop));
  }

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDTO>> getAllCrops(@PathVariable(name = "farmId") Long farmId)
      throws CustomError {
    List<Crop> allCrops = farmService.findAllCropByFarm(farmId);
    List<CropDTO> allCropsDto = allCrops.stream().map(crop -> new CropDTO(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarmId().getId()))
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(allCropsDto);
  }
}
