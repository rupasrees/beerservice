package com.example.beerservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.beerservice.entity.Manufacturer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class ManufacturerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ManufacturerRepository repository;

  @Test
  public void test_FindByCompany() throws Exception {
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setCompany("Heiniken");
    this.entityManager.persist(manufacturer);
    Manufacturer manufacturerDB = this.repository.findByCompany("Heiniken");
    assertThat(manufacturerDB.getCompany()).isEqualTo("Heiniken");

  }

}
