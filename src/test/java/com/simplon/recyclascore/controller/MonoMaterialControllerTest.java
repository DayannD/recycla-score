package com.simplon.recyclascore.controller;

import com.simplon.recyclascore.models.Enum.EnumMaterial;
import com.simplon.recyclascore.models.MonoMaterial;
import com.simplon.recyclascore.services.IServices.IMonoMaterialService;
import com.simplon.recyclascore.web.controller.MonoMaterialController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MonoMaterialControllerTest {

  @InjectMocks
  MonoMaterialController monoMaterialController;

  @Mock
  IMonoMaterialService monoMaterialService;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(monoMaterialController).build();
  }

  @Test
  void addMonoMaterial_ValidInput_ReturnsOk() throws Exception {
    mockMvc.perform(post("/api/monomaterial")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Wood\",\"material\":\"ACIER_INOX\",\"recyclability\":\"High\"}"))
      .andExpect(status().isOk());
  }

  @Test
  void getAllMonoMaterialsByMarerial_ValidMaterial_ReturnsOk() throws Exception {
    mockMvc.perform(get("/api/monomaterial/material/ACIER_INOX"))
      .andExpect(status().isOk());
  }

  @Test
  void getAllMonoMaterials_ReturnsOk() throws Exception {
    when(monoMaterialService.getAllMonoMaterials())
      .thenReturn(Arrays.asList(new MonoMaterial(), new MonoMaterial()));

    mockMvc.perform(get("/api/monomaterial"))
      .andExpect(status().isOk());
  }

  @Test
  void getMonoMaterialById_ValidId_ReturnsOk() throws Exception {
    mockMvc.perform(get("/api/monomaterial/1"))
      .andExpect(status().isOk());
  }

  @Test
  void updateMonoMaterial_ValidInput_ReturnsOk() throws Exception {
    mockMvc.perform(put("/api/monomaterial")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":1,\"name\":\"POLY_EXPAN\",\"material\":\"POLY_EXPAN\",\"recyclability\":\"High\"}"))
      .andExpect(status().isOk());
  }

  @Test
  void deleteMonoMaterial_ValidId_ReturnsOk() throws Exception {
    when(monoMaterialService.getMonoMaterialById(anyInt())).thenReturn(new MonoMaterial());

    mockMvc.perform(delete("/api/monomaterial/1"))
      .andExpect(status().isOk());
  }
}