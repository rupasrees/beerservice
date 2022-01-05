package com.example.beerservice.controller;

import com.example.beerservice.entity.Beer;
import com.example.beerservice.exception.ValidationException;
import com.example.beerservice.service.BeerService;
import com.example.beerservice.vo.BeerVO;
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
public class BeerController {

  @Autowired
  private BeerService beerService;


  @GetMapping("/beers")
  public List<BeerVO> getAllBeers(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size) {
    return beerService.getBeers(page, size);
  }

  @GetMapping("/beer/{id}")
  public Beer getBeerDetails(@PathVariable final String id) {
    return beerService.getBeerDetail(Long.parseLong(id));
  }


  @PostMapping(value = "/addBeer", consumes = "application/json")
  public ResponseEntity<Object> addBeer(@RequestBody BeerVO beerVO)
      throws EntityNotFoundException, ValidationException {
    beerService.addBeer(beerVO);
    return new ResponseEntity<>("Beer added to the catalogue", HttpStatus.OK);
  }

  @PutMapping(value = "/updateBeer/{id}", consumes = "application/json")
  public ResponseEntity<Object> updateBeer(@PathVariable final String id,
      @RequestBody BeerVO beerVO)
      throws EntityNotFoundException, ValidationException {
    beerService.updateBeer(Long.parseLong(id), beerVO);
    return new ResponseEntity<>("Beer updated to the catalogue", HttpStatus.OK);
  }


}
