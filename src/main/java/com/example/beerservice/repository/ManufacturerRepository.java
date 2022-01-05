package com.example.beerservice.repository;

import com.example.beerservice.entity.Manufacturer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ManufacturerRepository extends PagingAndSortingRepository<Manufacturer, Long> {

  Manufacturer findByCompany(String companyName);

}
