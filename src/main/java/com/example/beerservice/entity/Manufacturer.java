package com.example.beerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Manufacturer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long manufacturerId;

  @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnoreProperties("manufacturer")
  private List<Beer> beers = new ArrayList<>();

  @Column(unique = true, nullable = false)
  private String company;

  private String nationality;

  public void addBeer(Beer beer) {
    getBeers().add(beer);
  }


  @Override
  public String toString() {
    return "manufacturer [id=" + manufacturerId + "nationality=" + nationality + ", company="
        + company + "]";
  }

}
