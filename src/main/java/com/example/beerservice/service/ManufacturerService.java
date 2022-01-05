package com.example.beerservice.service;

import com.example.beerservice.entity.Manufacturer;
import com.example.beerservice.exception.ValidationException;
import com.example.beerservice.repository.ManufacturerRepository;
import com.example.beerservice.vo.ManufacturerVO;
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
public class ManufacturerService {

  @Autowired
  private ManufacturerRepository manufacturerRepository;

  public void addManufacturer(ManufacturerVO manufacturerVO)
      throws ValidationException {

    Manufacturer manufacturer = manufacturerRepository
        .findByCompany(manufacturerVO.getName());
    if (manufacturer != null) {
      throw new ValidationException("Manufacturer already listed");
    }
    manufacturer = new Manufacturer();
    manufacturer.setCompany(manufacturerVO.getName());
    manufacturer.setNationality(manufacturerVO.getNationality());
    try {
      manufacturerRepository.save(manufacturer);
    } catch (DataIntegrityViolationException e) {
      throw new ValidationException("Error in listing Manufacturer.Please contact administrator",
          e);
    }
  }

  public void updateManufacturer(Long id, ManufacturerVO manufacturerVO)
      throws ValidationException {

    Manufacturer manufacturer = manufacturerRepository
        .findById(id).orElse(null);
    if (manufacturer == null) {
      throw new EntityNotFoundException("Manufacturer not found");
    }
    if (Strings.isNotBlank(manufacturerVO.getName())) {
      manufacturer.setCompany(manufacturerVO.getName());
    }
    if (Strings.isNotBlank(manufacturerVO.getNationality())) {
      manufacturer.setNationality(manufacturerVO.getNationality());
    }
    try {
      manufacturerRepository.save(manufacturer);
    } catch (DataIntegrityViolationException e) {
      throw new ValidationException("Error in listing Manufacturer.Please contact administrator",
          e);
    }
  }

  public List<Manufacturer> getManufacturer(int page, int size) {
    Sort.Order order = new Sort.Order(Sort.Direction.ASC, "company").ignoreCase();
    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(order));
    Page<Manufacturer> manufacturers = manufacturerRepository
        .findAll(pageRequest);
    return manufacturers.getContent();
  }

  public Manufacturer getManufacturerDetail(Long id) {
    Manufacturer manufacturer = manufacturerRepository.findById(id).orElse(null);
    if (manufacturer == null) {
      throw new EntityNotFoundException("Manufacturer not found");
    }
    return manufacturer;
  }
}
