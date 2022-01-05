package com.example.beerservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.beerservice.entity.Manufacturer;
import com.example.beerservice.repository.ManufacturerRepository;
import com.example.beerservice.vo.ManufacturerVO;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceTest {

  @InjectMocks
  private ManufacturerService manufacturerService;

  @Mock
  private ManufacturerRepository manufacturerRepository;

  @BeforeEach
  void initUseCase() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  public void test_getManufacturer() throws Exception {

    Page<Manufacturer> manufacturers = Mockito.mock(Page.class);
    when(manufacturerRepository.findAll(any(PageRequest.class))).thenReturn(manufacturers);
    manufacturerService.getManufacturer(0, 10);
    verify(manufacturerRepository, times(1)).findAll(any(PageRequest.class));
  }


  @Test
  public void test_getManufacturerDetail() throws Exception {
    when(manufacturerRepository.findById(anyLong())).thenReturn(Optional.of(new Manufacturer()));
    Manufacturer manufacturer = manufacturerService.getManufacturerDetail(1L);
    assertThat(manufacturer).isNotNull();
    verify(manufacturerRepository, times(1)).findById(anyLong());
  }

  @Test
  public void test_addManufacturer() throws Exception {
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setCompany("Beer Company");
    when(manufacturerRepository.save(any(Manufacturer.class))).thenReturn(manufacturer);
    manufacturerService.addManufacturer(new ManufacturerVO());
    verify(manufacturerRepository, times(1)).save(any((Manufacturer.class)));
  }

  @Test
  public void test_updateManufacturer() throws Exception {
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setCompany("beer company");
    when(manufacturerRepository.findById(anyLong())).thenReturn(Optional.of(manufacturer));
    when(manufacturerRepository.save(any(Manufacturer.class))).thenReturn(manufacturer);
    manufacturerService.updateManufacturer(1L, new ManufacturerVO());
    verify(manufacturerRepository, times(1)).save(any((Manufacturer.class)));
  }

}
