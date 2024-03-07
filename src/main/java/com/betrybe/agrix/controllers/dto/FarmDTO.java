package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.util.List;

public record FarmDTO(String name, Double size, List<Crop> crop) {

  public static FarmDTO fromEntityToDto(Farm farm) {
    return new FarmDTO(farm.getName(), farm.getSize(), farm.getCrops());
  }

  public Farm dtoToEntity() {
    return new Farm(name, size, crop);
  }
}
