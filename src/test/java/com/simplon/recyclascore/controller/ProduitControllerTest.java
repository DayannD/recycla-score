package com.simplon.recyclascore.controller;

import com.simplon.recyclascore.models.dto.InfosProduitDTO;
import com.simplon.recyclascore.models.mappers.ProduitMapper;
import com.simplon.recyclascore.services.IServices.IProduitService;
import com.simplon.recyclascore.web.controller.ProduitController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProduitControllerTest {

  @InjectMocks
  ProduitController produitController;

  @Mock
  ProduitMapper produitMapper;

  @Mock
  IProduitService produitService;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(produitController).build();
  }

  @Test
  public void getProduitByName_ValidTag_ReturnsOk() throws Exception {
    when(produitService.findByName(anyString())).thenReturn(Optional.of(new InfosProduitDTO(
      "nomProduit",
      "description",
      1.0f,
      1.0f,
      1.0f,
      new byte[0],
      new ArrayList<>()
    )));

    mockMvc.perform(get("/api/produit/VHU"))
      .andExpect(status().isOk());
  }

  @Test
  public void getProduitByName_InvalidTag_ReturnsNotFound() throws Exception {
    when(produitService.findByName(anyString())).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/produit/invalidTag"))
      .andExpect(status().isBadRequest());
  }


//TODO: fix this test
//  @Test
//  public void getAllProduitsByTag_ValidName_ReturnsOk() throws Exception {
//    String name = "Produit A";
//
//    when(produitService.findByName(anyString())).thenReturn(Optional.of(new InfosProduitDTO(
//      "nomProduit",
//      "description",
//      1.0f,
//      1.0f,
//      1.0f,
//      "urlImage",
//      new ArrayList<>()
//    )));
//
//    mockMvc.perform(get("/api/produit/infos/" + name))
//      .andExpect(status().isOk());
//  }

  @Test
  public void setTagProduits_ReturnsOk() throws Exception {
    mockMvc.perform(get("/api/produit/BATTERIES_D3E"))
      .andExpect(status().isOk());
  }

  @Test
  public void setTagProduits_ReturnsBadRequest() throws Exception {
    mockMvc.perform(get("/api/produit/badRequest"))
      .andExpect(status().isBadRequest());
  }
}
