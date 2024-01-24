package com.simplon.recyclascore.controller;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.models.Materiaux;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import com.simplon.recyclascore.services.MateriauxService;
import com.simplon.recyclascore.web.controller.AdminController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

  @InjectMocks
  private AdminController adminController;

  @Mock
  private IMateriauxService materiauxService;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
  }

  @Test
  public void createMateriaux_HappyPath() throws Exception {
    MateriauxDTO materiauxDTO = new MateriauxDTO("Wood", "Recycle", 10.0f, 20.0f);

    mockMvc.perform(post("/api/admin")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"nomMateriau\":\"Wood\",\"typeRecyclage\":\"Recycle\",\"coutRecyclage\":10.0,\"energieRecyclage\":20.0}"))
      .andExpect(status().isCreated());
  }

  @Test
  public void createMateriaux_InvalidInput_ReturnsBadRequest() throws Exception {
    mockMvc.perform(post("/api/admin")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"nomMateriau\":\"\",\"typeRecyclage\":\"\",\"coutRecyclage\":-1,\"energieRecyclage\":-1}"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void createMateriaux_EmptyBody() throws Exception {
    mockMvc.perform(post("/api/admin")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{}"))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void createMateriaux_MissingFields() throws Exception {
    mockMvc.perform(post("/api/admin")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"nomMateriau\":\"Wood\"}"))
      .andExpect(status().isBadRequest());
  }
}