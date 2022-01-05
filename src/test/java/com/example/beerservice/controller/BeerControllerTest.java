package com.example.beerservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.beerservice.service.BeerService;
import com.example.beerservice.vo.BeerVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest({BeerController.class})
public class BeerControllerTest {

  @MockBean
  BeerService beerService;
  @Autowired
  private MockMvc mockMvc;

  public static String fromJsonToString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void test_addBeer() throws Exception {

    BeerVO beerVO = new BeerVO("bear", "", "", "", "bear company");

    mockMvc.perform(
        MockMvcRequestBuilders.post("/addBeer")
            .content(fromJsonToString(beerVO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Beer added to the catalogue"));
  }


  @Test
  public void test_getBeers() throws Exception {
    BeerVO beerVO = new BeerVO("bear", "", "", "", "bear company");
    List<BeerVO> beerVOS = Collections.singletonList(beerVO);

    when(beerService.getBeers(0, 10)).thenReturn(beerVOS);
    mockMvc.perform(
        MockMvcRequestBuilders.get("/beers").param("page", "0")
            .param("size", "10"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{}]"));
  }
}
