package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;

public record CropDTO(Long id, String name, Double plantedArea, Long farmId) {

  public static CropDTO fromEntityToDto(Crop crop) {
    return new CropDTO(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarmId().getId());
  }

}
