package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.models.DTO.MonoMaterialDTO;
import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.services.IServices.IMateriauxService;
import com.simplon.recyclascore.services.IServices.IMonoMaterialService;
import com.simplon.recyclascore.services.IServices.IProduitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final IMateriauxService materiauxService;
  private final IMonoMaterialService monoMaterialService;
  private final IProduitService produitService;

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

  /**
   * @param monoMaterialDTO
   * @return String
   * @PostMapping("") : crée un monomaterial
   */
  @PostMapping("/mono-material")
  @ResponseStatus(HttpStatus.CREATED)
  public void addMonoMaterial(@Valid @RequestBody MonoMaterialDTO monoMaterialDTO) {
    this.monoMaterialService.addMonoMaterial(monoMaterialDTO);
  }
  @PostMapping("/produit")
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduit(@Valid @ModelAttribute ProduitsDTO produitsDTO) {
    try {
      this.produitService.save(produitsDTO);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/tags")
  public String getTags() {
    System.out.println("JE SUIS DANS LE CONTROLLER");
    return "test";
//    return this.produitService.getAlltags();
  }
}

