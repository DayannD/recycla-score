package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.models.DTO.ProduitOutDTO;
import com.simplon.recyclascore.models.Enum.EnumTag;
import com.simplon.recyclascore.services.IServices.IProduitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/produit")
public class ProduitController {

private final IProduitService produitService;

  /**
   * @param tag
   * @return ResponseEntity<List<ProduitsDTO>>
   * @GetMapping("/{tag}") : récupère tous les produits en fonction du tag
   */
  @GetMapping("/{tag}")
  public ResponseEntity<List<ProduitOutDTO>> getAllProduits(@PathVariable EnumTag tag) {
    List<ProduitOutDTO> produitOutDTO = this.produitService.getAllProduits(tag);
    for (ProduitOutDTO produit : produitOutDTO) {
      log.info(produit.file().toString());
      log.info(produit.file().length + "");
      int length = produit.file().length;
    }
    return ResponseEntity.ok(this.produitService.getAllProduits(tag));
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

  @GetMapping("/all")
  public ResponseEntity<List<ProduitOutDTO>> getAllProduits() {
    return ResponseEntity.ok(this.produitService.getAllProduits());
  }

  @GetMapping("/details/{id}")
  public ResponseEntity<InfosProduitDTO> getProduitById(@PathVariable int id) {
    log.warn("PRODUIT DTO : " + this.produitService.findById(id).get());
    return this.produitService.findById(id)
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
