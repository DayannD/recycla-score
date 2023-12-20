package com.simplon.recyclascore.web.controller;

import com.simplon.recyclascore.models.DTO.InfosProduitDTO;
import com.simplon.recyclascore.services.IServices.IProduitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/produit")
public class ProduitController {

private final IProduitService produitService;
  @GetMapping("/infos/{name}")
  public ResponseEntity<InfosProduitDTO> getProduitByName(@PathVariable String name) {
    return this.produitService.findByName(name)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
