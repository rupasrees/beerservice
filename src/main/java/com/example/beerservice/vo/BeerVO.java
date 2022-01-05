package com.example.beerservice.vo;

import lombok.Data;

@Data
public class BeerVO {

  private String manufacturerName;

  private String name;

  private String type;

  private String description;

  private String graduation;

  public BeerVO(String name, String type, String description, String graduation,
      String manufacturerName) {
    this.name = name;
    this.type = type;
    this.description = description;
    this.graduation = graduation;
    this.manufacturerName = manufacturerName;
  }


}
