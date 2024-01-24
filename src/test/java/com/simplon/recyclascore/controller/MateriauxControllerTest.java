package com.simplon.recyclascore.controller;

import com.simplon.recyclascore.services.IServices.IMateriauxService;

import com.simplon.recyclascore.web.controller.MateriauxController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MateriauxControllerTest {


  @Autowired
  MateriauxController materiauxController;

  @Autowired
  IMateriauxService materiauxService;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(materiauxController).build();
  }

  @Test
  void getAllMateriaux_ReturnsOk() throws Exception {
    mockMvc.perform(get("/api/materiaux"))
      .andExpect(status().isOk());
  }

  @Test
  void getMateriauxById_ValidId_ReturnsOk() throws Exception {
    mockMvc.perform(get("/api/materiaux/1"))
      .andExpect(status().isOk());
  }

  @Test
  void getMateriauxById_InvalidId_ReturnsNotFound() throws Exception {
    mockMvc.perform(get("/api/materiaux/10000000"))
      .andExpect(status().isNotFound());
  }
}
