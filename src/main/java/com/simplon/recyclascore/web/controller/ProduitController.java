package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.ProduitsDTO;
import com.simplon.recyclascore.models.Enum.EnumTag;
import com.simplon.recyclascore.services.IServices.IProduitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/produit")
public class ProduitController {

private final IProduitService produitService;

  /**
   * @param tag
   * @return ResponseEntity<List<ProduitsDTO>>
   * @GetMapping("/{tag}") : récupère tous les produits en fonction du tag
   */
  @GetMapping("/{tag}")
  public ResponseEntity<List<ProduitsDTO>> getAllProduits(@PathVariable EnumTag tag) {
    return ResponseEntity.ok(this.produitService.getALlProduits(tag));
  }

  /**
   * @param name
   * @return ResponseEntity<InfosProduitDTO>
   * @GetMapping("/infos/{name}") : récupère les infos d'un produit en fonction de son nom
   */
  @GetMapping("/infos/{name}")
  public ResponseEntity<InfosProduitDTO> getProduitByName(@PathVariable String name) {
    return this.produitService.findByName(name)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * @GetMapping("/set") : set les tags des produits
   */
  @GetMapping("/set")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void setProduits() {
    this.produitService.setTagProduits();
  }

}
