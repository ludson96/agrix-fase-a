package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "crops")
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Double plantedArea;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farmId;

  public Crop () {

  }

  public Crop(String name, Double plantedArea, Farm farmId) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.farmId = farmId;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPlantedArea() {
    return plantedArea;
  }

  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  public Farm getFarmId() {
    return farmId;
  }

  public void setFarmId(Farm farmId) {
    this.farmId = farmId;
  }
}
