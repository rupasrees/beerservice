package com.example.beerservice.repository;

import com.example.beerservice.entity.Beer;
import com.example.beerservice.vo.BeerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeerRepository extends PagingAndSortingRepository<Beer, Long>,
    JpaSpecificationExecutor<Beer> {

  Beer findByName(String name);

  @Query(value = "Select new com.example.beerservice.vo.BeerVO(b.name, b.type, "
      + "b.description, b.graduation, m.company)  from Beer b , Manufacturer m where b.manufacturer.manufacturerId = m.manufacturerId")
  Page<BeerVO> getListOfBeers(Pageable pageable);


}
