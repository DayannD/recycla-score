package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.MateriauxDTO;
import com.simplon.recyclascore.models.DTO.MonoMaterialDTO;
import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.models.MonoMaterial;
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
import java.util.List;

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
  @PostMapping("/materiau")
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
  @PostMapping(value = "/produit", consumes = "multipart/form-data")
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduit(@Valid @RequestBody ProduitsDTO produitsDTO) throws IOException {
    System.out.println("IM HEERRREE");
//    try {
//      this.produitService.save(produitsDTO);
//    } catch (IllegalArgumentException e) {
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//    } catch (IOException e) {
//      throw new IOException(e);
//    }
  }

  @GetMapping("/tags")
  public List<String> getTags() {
    return List.of(this.produitService.getAlltags());
  }

  @DeleteMapping("/mono-material/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMonoMaterial(@PathVariable int id) {
    this.monoMaterialService.deleteMonoMaterialById(id);
  }

  @PutMapping("/mono-material")
  public void updateMonoMaterial(@RequestBody MonoMaterial monoMaterial) {
    this.monoMaterialService.updateMonoMaterial(monoMaterial);
  }

  @DeleteMapping("/produit/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduit(@PathVariable int id) {
    this.produitService.deleteById(id);
  }

  @DeleteMapping("/materiau/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMateriau(@PathVariable int id) {
    this.materiauxService.deleteById(id);
  }

  @PutMapping("/materiau")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateMateriau(@Valid @RequestBody MateriauxDTO materiauxDTO) {
    this.materiauxService.updateMateriau(materiauxDTO);
  }
}

