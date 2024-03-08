package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDTO;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crops")
public class CropController {

  private FarmService farmService;

  @Autowired
  public CropController(FarmService farmService) {
    this.farmService = farmService;
  }

  @GetMapping
  public ResponseEntity<List<CropDTO>> getAllCrops() {
    List<Crop> allCrops = farmService.findAllCrops();

    List<CropDTO> allCropsDto = allCrops.stream().map(crop -> new CropDTO(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarmId().getId()
    )).collect(Collectors.toList());

    return ResponseEntity.ok().body(allCropsDto);
  }
}
