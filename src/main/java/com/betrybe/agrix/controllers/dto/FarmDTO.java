package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

public record FarmDTO(String name, Double size) {

  public static FarmDTO fromEntityToDto(Farm farm) {
    return new FarmDTO(farm.getName(), farm.getSize());
  }

  public Farm dtoToEntity() {
    return new Farm(name, size);
  }
}
