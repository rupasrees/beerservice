package com.example.beerservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.beerservice.entity.Beer;
import com.example.beerservice.entity.Manufacturer;
import com.example.beerservice.repository.BeerRepository;
import com.example.beerservice.repository.ManufacturerRepository;
import com.example.beerservice.vo.BeerVO;
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
public class BeerServiceTest {

  @InjectMocks
  private BeerService beerService;

  @Mock
  private BeerRepository beerRepository;
  @Mock
  private ManufacturerRepository manufacturerRepository;

  @BeforeEach
  void initUseCase() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  public void test_getBeers() throws Exception {

    Page<BeerVO> beers = Mockito.mock(Page.class);
    when(beerRepository.getListOfBeers(any(PageRequest.class))).thenReturn(beers);
    beerService.getBeers(0, 10);
    verify(beerRepository, times(1)).getListOfBeers(any(PageRequest.class));
  }


  @Test
  public void test_getBeerDetail() throws Exception {
    when(beerRepository.findById(anyLong())).thenReturn(Optional.of(new Beer()));
    Beer beer = beerService.getBeerDetail(1L);
    assertThat(beer).isNotNull();
    verify(beerRepository, times(1)).findById(anyLong());
  }

  @Test
  public void test_addBear() throws Exception {
    BeerVO beerVO = new BeerVO("bear", "", "", "", "bear company");
    Beer beer = new Beer();
    beer.setName(beerVO.getName());
    beer.setType(beerVO.getType());
    beer.setDescription(beerVO.getDescription());
    beer.setGraduation(beerVO.getGraduation());
    Manufacturer manufacturer = new Manufacturer();
    when(manufacturerRepository.findByCompany(anyString())).thenReturn(manufacturer);
    when(beerRepository.save(any(Beer.class))).thenReturn(beer);
    beerService.addBeer(beerVO);
    assertThat(beer).isNotNull();
    verify(beerRepository, times(1)).save(any((Beer.class)));
  }

  @Test
  public void test_addBear_AlreadyExist() throws Exception {
    BeerVO beerVO = new BeerVO("bear", "", "", "", "bear company");
    Beer beer = new Beer();
    beer.setName(beerVO.getName());
    when(beerRepository.findByName(anyString())).thenReturn(beer);
    assertThatThrownBy(() -> beerService.addBeer(beerVO)).hasMessage(("Beer is already listed"));
  }

  @Test
  public void test_addBear_Manufacturer_NotFound() throws Exception {
    BeerVO beerVO = new BeerVO("bear", "", "", "", "bear company");
    when(manufacturerRepository.findByCompany(anyString())).thenReturn(null);
    assertThatThrownBy(() -> beerService.addBeer(beerVO)).hasMessage(
        ("Manufacturer for this Beer is not Listed.Please add details for manufacturer"));
  }


  @Test
  public void test_updateBeer() throws Exception {
    BeerVO beerVO = new BeerVO("bear", "", "", "", "bear company");
    Beer beer = new Beer();
    beer.setName(beerVO.getName());
    beer.setType(beerVO.getType());
    beer.setDescription(beerVO.getDescription());
    beer.setGraduation(beerVO.getGraduation());
    Manufacturer manufacturer = new Manufacturer();
    manufacturer.setCompany(beerVO.getManufacturerName());
    beer.setManufacturer(manufacturer);
    when(beerRepository.findById(anyLong())).thenReturn(Optional.of(beer));
    when(beerRepository.save(any(Beer.class))).thenReturn(beer);
    beerService.updateBeer(1, beerVO);
    assertThat(beer).isNotNull();
    verify(beerRepository, times(1)).save(any((Beer.class)));
  }
}
