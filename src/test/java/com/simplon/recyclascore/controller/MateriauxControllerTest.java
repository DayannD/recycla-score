package com.simplon.recyclascore.controller;

import com.simplon.recyclascore.models.Materiaux;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import com.simplon.recyclascore.web.controller.MateriauxController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MateriauxControllerTest {

  @InjectMocks
  MateriauxController materiauxController;

  @Mock
  IMateriauxService materiauxService;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setup() {
    // No need to initialize mockMvc here, it's automatically injected by @AutoConfigureMockMvc
  }

  @Test
  void getAllMateriaux_ReturnsOk() throws Exception {
    // Mock the service method
    when(materiauxService.findAll()).thenReturn(new ArrayList<>());

    mockMvc.perform(get("/api/materiaux"))
      .andExpect(status().isOk())
      .andExpect(content().json("[]")); // Expect an empty JSON array
  }

  @Test
  void getMateriauxById_ValidId_ReturnsOk() throws Exception {
    when(materiauxService.findById(4)).thenReturn(new Materiaux());

    mockMvc.perform(get("/api/materiaux/4"))
      .andExpect(status().isOk())
      .andExpect(content().json("{\"id\":4, \"name\":\"Materiaux 4\"}")); // Expect a JSON object with id and name
  }

  @Test
  void getMateriauxById_InvalidId_ReturnsNotFound() throws Exception {
    // Mock the service method
    when(materiauxService.findById(10000000)).thenReturn(null);

    mockMvc.perform(get("/api/materiaux/10000000"))
      .andExpect(status().isNotFound());
  }
}