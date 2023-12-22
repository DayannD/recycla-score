package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final IMateriauxService materiauxService;

  /**
   * @param materiauxDTO
   * @PostMapping() : crée un materiaux
   */
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void createMateriaux(@RequestBody MateriauxDTO materiauxDTO) {
    this.materiauxService.save(materiauxDTO);
  }
}
