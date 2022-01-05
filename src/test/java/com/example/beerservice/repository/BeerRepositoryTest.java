package com.example.beerservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.beerservice.entity.Beer;
import com.example.beerservice.entity.Manufacturer;
import com.example.beerservice.vo.BeerVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class BeerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  @Autowired
  private BeerRepository beerRepository;

  @Test
  public void test_FindByName() throws Exception {
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setCompany("Heiniken");
    this.entityManager.persist(manufacturer);
    Beer beer = new Beer();
    beer.setName("Sample");
    beer.setManufacturer(manufacturer);
    this.entityManager.persist(beer);
    Beer beerDb = this.beerRepository.findByName("Sample");
    assertThat(beerDb.getName()).isEqualTo("Sample");
  }

  @Test
  public void test_GetBeers() throws Exception {
    PageRequest pageRequest = PageRequest.of(0, 5);
    Page<BeerVO> beers = this.beerRepository.getListOfBeers(pageRequest);
    assertThat(beers.getContent().size()).isEqualTo(1);
  }
}
