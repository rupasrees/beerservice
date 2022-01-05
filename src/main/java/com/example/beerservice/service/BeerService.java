package com.example.beerservice.service;


import com.example.beerservice.entity.Beer;
import com.example.beerservice.entity.Manufacturer;
import com.example.beerservice.exception.ValidationException;
import com.example.beerservice.repository.BeerRepository;
import com.example.beerservice.repository.ManufacturerRepository;
import com.example.beerservice.vo.BeerVO;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

  @Autowired
  private BeerRepository beerRepository;

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  public void addBeer(BeerVO beerVO)
      throws EntityNotFoundException, ValidationException {
    Beer beer;
    beer = beerRepository.findByName(beerVO.getName());
    if (beer != null) {
      throw new ValidationException("Beer is already listed");
    }
    beer = new Beer();
    beer.setName(beerVO.getName());
    beer.setType(beerVO.getType());
    beer.setDescription(beerVO.getDescription());
    beer.setGraduation(beerVO.getGraduation());

    Manufacturer manufacturer = manufacturerRepository
        .findByCompany(beerVO.getManufacturerName());
    if (manufacturer == null) {
      throw new EntityNotFoundException(
          "Manufacturer for this Beer is not Listed.Please add details for manufacturer");
    }
    manufacturer.getBeers().add(beer);
    beer.setManufacturer(manufacturer);
    try {
      beerRepository.save(beer);
    } catch (DataIntegrityViolationException e) {
      throw new ValidationException("Error in listing Beer.Please contact administrator", e);

    }
  }


  public void updateBeer(long beerId, BeerVO beerVO)
      throws EntityNotFoundException, ValidationException {

    Beer beer = beerRepository.findById(beerId).orElse(null);
    if (beer == null) {
      throw new EntityNotFoundException("Beer not found for updating catalogue");
    }
    if (Strings.isNotBlank(beerVO.getName())) {
      beer.setName(beerVO.getName());
    }
    if (Strings.isNotBlank(beerVO.getType())) {
      beer.setType(beerVO.getType());
    }
    if (Strings.isNotBlank(beerVO.getDescription())) {
      beer.setDescription(beerVO.getDescription());
    }
    if (Strings.isNotBlank(beerVO.getGraduation())) {
      beer.setGraduation(beerVO.getGraduation());
    }
    if (Strings.isNotBlank(beerVO.getManufacturerName()) && !beerVO
        .getManufacturerName()
        .equalsIgnoreCase(beer.getManufacturer().getCompany())) {
      throw new ValidationException("Manufacturer name can not be updated");
    }
    try {
      beerRepository.save(beer);
    } catch (DataIntegrityViolationException e) {
      throw new ValidationException("Error in listing Beer.Please check input", e);

    }
  }

  public List<BeerVO> getBeers(int page, int size) {
    Sort.Order order = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(order));
    Page<BeerVO> beers = beerRepository.getListOfBeers(pageRequest);
    return beers.getContent();
  }

  public Beer getBeerDetail(Long id) {
    Beer beer = beerRepository.findById(id).orElse(null);
    if (beer == null) {
      throw new EntityNotFoundException("Beer not found");
    }
    return beer;
  }
}
