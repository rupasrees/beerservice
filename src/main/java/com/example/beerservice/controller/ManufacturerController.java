package com.example.beerservice.controller;

import com.example.beerservice.entity.Manufacturer;
import com.example.beerservice.exception.ValidationException;
import com.example.beerservice.service.ManufacturerService;
import com.example.beerservice.vo.ManufacturerVO;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManufacturerController {

  @Autowired
  private ManufacturerService manufacturerService;

  @GetMapping("/manufacturers")
  public List<Manufacturer> searchManufacturer(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    return manufacturerService.getManufacturer(page, size);
  }

  @GetMapping("/manufacturer/{id}")
  public Manufacturer getManufacturerDetails(@PathVariable final String id) {
    return manufacturerService.getManufacturerDetail(Long.parseLong(id));
  }

  @PostMapping(value = "/addManufacturer", consumes = "application/json")
  public ResponseEntity<Object> addManufacturer(
      @RequestBody ManufacturerVO manufacturerVO)
      throws ValidationException {
    manufacturerService.addManufacturer(manufacturerVO);
    return new ResponseEntity<>("Manufacturer added", HttpStatus.OK);
  }

  @PutMapping(value = "/updateManufacturer/{id}", consumes = "application/json")
  public ResponseEntity<Object> updateManufacturer(@PathVariable final String id,
      @RequestBody ManufacturerVO manufacturerVO)
      throws EntityNotFoundException, ValidationException {
    manufacturerService.updateManufacturer(Long.parseLong(id), manufacturerVO);
    return new ResponseEntity<>("Manufacturer updated to the catalogue", HttpStatus.OK);
  }
}
