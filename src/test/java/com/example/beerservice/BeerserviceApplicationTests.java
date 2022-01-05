package com.example.beerservice;

import com.example.beerservice.controller.BeerController;
import com.example.beerservice.controller.ManufacturerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerserviceApplicationTests {

  @Autowired
  private BeerController beerController;

  @Autowired
  private ManufacturerController manufacturerController;

  @Test
  void contextLoads() {
    Assertions.assertThat(beerController).isNotNull();
    Assertions.assertThat(manufacturerController).isNotNull();
  }

}
