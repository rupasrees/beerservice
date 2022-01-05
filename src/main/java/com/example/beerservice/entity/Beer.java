package com.example.beerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Beer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long beerId;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(name = "beertype")
  private String type;

  private String graduation;
  private String description;


  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "manufacturerId")
  @JsonIgnoreProperties("beers")
  private Manufacturer manufacturer;


  @Override
  public String toString() {
    return "beer [id=" + beerId + "name=" + name + ", description=" + description + ", type=" + type
        + ", graduation="
        + graduation + "]";
  }
}
