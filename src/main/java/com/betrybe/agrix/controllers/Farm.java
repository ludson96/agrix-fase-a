package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FarmDTO;
import com.betrybe.agrix.services.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Farm {
  private final FarmService farmService;

  @Autowired
  public Farm(FarmService farmService) {
    this.farmService = farmService;
  }

  public ResponseEntity<ResponseFarmDto> insertFarm(@RequestBody FarmDTO farmDTO) {
    return farmService.insertFarm(farmDTO.dtoToEntity());
  }
}
