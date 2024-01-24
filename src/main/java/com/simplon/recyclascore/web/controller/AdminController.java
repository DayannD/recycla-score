package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
  public void createMateriaux(@Valid @RequestBody MateriauxDTO materiauxDTO) {
    try {
      this.materiauxService.save(materiauxDTO);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
