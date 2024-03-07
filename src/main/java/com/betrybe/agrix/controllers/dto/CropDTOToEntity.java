package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;

public record CropDTOToEntity(String name, Double plantedArea, Farm farmId) {
  public Crop dtoToEntity() {
    return new Crop(name, plantedArea, farmId);
  }
}
